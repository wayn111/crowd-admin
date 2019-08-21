package com.wayn.notify.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.domain.User;
import com.wayn.commom.service.UserOnlineService;
import com.wayn.commom.service.UserService;
import com.wayn.commom.util.AsyncExecutorUtil;
import com.wayn.framework.util.ShiroUtil;
import com.wayn.notify.dao.NotifyDao;
import com.wayn.notify.domain.Notify;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.service.NotifyRecordService;
import com.wayn.notify.service.NotifyService;
import com.wayn.notify.util.TimertaskManger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 通知通告 服务层实现
 *
 * @author wayn
 * @date 2019-08-10
 */
@Service
public class NotifyServiceImpl extends ServiceImpl<NotifyDao, Notify> implements NotifyService {
    @Autowired
    private NotifyDao notifyDao;

    @Autowired
    private UserService userService;

    @Autowired

    private NotifyRecordService notifyRecordService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserOnlineService userOnlineService;

    /**
     * 查询通知通告信息
     *
     * @param page
     * @return 通知通告信息
     */
    @Override
    public Page<Notify> selectNotifyList(Page<Notify> page, Notify notify) {
        List<Notify> records = notifyDao.selectNotifyList(page, notify);
        return page.setRecords(records);
    }

    @Transactional
    @Override
    public boolean save(Notify notify, String receiveUserIds) {
        notify.setCreateTime(new Date());
        notify.setCreateBy(ShiroUtil.getSessionUid());
        if (notify.getPublishTime() == null) {
            notify.setPublishTime(new Date());
        }
        boolean insert = insert(notify);
        List<NotifyRecord> collect = new ArrayList<>();
        List<String> receiveUserList = fillNotifyRecordList(notify, receiveUserIds, collect);
        TimerTask task = getTimerTask(notify, receiveUserList);
        // 如果不是立即发布，则在指定时间发布通知
        if (notify.getNotifyState() != 1) {
            AsyncExecutorUtil.scheduled(task, notify.getPublishTime());
        } else {
            AsyncExecutorUtil.scheduled(task, DateUtils.addMilliseconds(new Date(), 1500));
        }
        notifyRecordService.insertBatch(collect);
        return insert;
    }

    @Transactional
    @Override
    public boolean update(Notify notify, String receiveUserIds) {
        notify.setUpdateTime(new Date());
        notify.setUpdateBy(ShiroUtil.getSessionUid());
        boolean update = updateById(notify);
        notifyRecordService.delete(new EntityWrapper<NotifyRecord>().eq("notifyId", notify.getId()));
        List<NotifyRecord> collect = new ArrayList<>();
        List<String> receiveUserList = fillNotifyRecordList(notify, receiveUserIds, collect);
        // 如果不是立即发布，则在指定时间发布通知
        TimerTask task = getTimerTask(notify, receiveUserList);
        if (notify.getNotifyState() != 1) {
            AsyncExecutorUtil.scheduled(task, notify.getPublishTime());
        } else {
            AsyncExecutorUtil.scheduled(task, DateUtils.addMilliseconds(new Date(), 1500));
        }
        notifyRecordService.insertBatch(collect);
        return update;
    }

    /**
     * 获取timerTask定时任务，在定时任务执行时，批量保存记录
     *
     * @param notify
     * @param receiveUserList
     * @return
     */
    public TimerTask getTimerTask(Notify notify, List<String> receiveUserList) {
        if (TimertaskManger.contains(notify.getId().toString())) {
            TimerTask task = TimertaskManger.get(notify.getId().toString());
            task.cancel();
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (User user : userOnlineService.listUser()) {
                    for (String userId : receiveUserList) {
                        if (userId.equals(user.getId())) {
                            simpMessagingTemplate.convertAndSendToUser(user.toString(), "/queue/notifications", "新消息：" + notify.getTitle());
                        }
                    }
                }
                updateForSet("notifyState = 1", new EntityWrapper<Notify>().eq("id", notify.getId()));
                TimertaskManger.remove(notify.getId().toString());
                this.cancel();
            }
        };
        TimertaskManger.put(notify.getId().toString(), task);
        return task;
    }

    @Override
    public boolean remove(Long id) {
        return updateForSet("delFlag = 1", new EntityWrapper<Notify>().eq("id", id));
    }

    @Override
    public boolean batchRemove(Long[] ids) {
        return updateForSet("delFlag = 1", new EntityWrapper<Notify>().in("id", Arrays.asList(ids)));
    }


    /**
     * 填充NotifyRecord集合记录
     *
     * @param notify
     * @param receiveUserIds
     * @param collect
     * @return 接受用户id集合
     */
    public List<String> fillNotifyRecordList(Notify notify, String receiveUserIds, List<NotifyRecord> collect) {
        List<String> split = new ArrayList<>();
        if (StringUtils.isEmpty(receiveUserIds)) {
            for (User user : userService.selectList(new EntityWrapper<User>())) {
                NotifyRecord notifyRecord = new NotifyRecord();
                notifyRecord.setCreateTime(new Date());
                notifyRecord.setRead(false);
                notifyRecord.setReceiveUserId(user.getId());
                notifyRecord.setReceiveUserName(user.getUserName());
                notifyRecord.setNotifyId(notify.getId());
                collect.add(notifyRecord);
                split.add(user.getId());
            }
        } else {
            split.addAll(Arrays.asList(receiveUserIds.split(",")));
            collect.addAll(new ArrayList<String>(split).stream().map(item -> {
                NotifyRecord notifyRecord = new NotifyRecord();
                notifyRecord.setCreateTime(new Date());
                notifyRecord.setRead(false);
                notifyRecord.setReceiveUserId(item);
                notifyRecord.setReceiveUserName(userService.selectById(item).getUserName());
                notifyRecord.setNotifyId(notify.getId());
                return notifyRecord;
            }).collect(Collectors.toList()));
        }
        return split;
    }
}
