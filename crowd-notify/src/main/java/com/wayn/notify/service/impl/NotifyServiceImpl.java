package com.wayn.notify.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.common.domain.User;
import com.wayn.common.service.UserService;
import com.wayn.common.shiro.util.ShiroUtil;
import com.wayn.notify.dao.NotifyDao;
import com.wayn.notify.domain.Notify;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.service.NotifyRecordService;
import com.wayn.notify.service.NotifyService;
import com.wayn.notify.util.NotifyScheduleUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private Scheduler scheduler;


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
        boolean insert = save(notify);
        List<NotifyRecord> collect = new ArrayList<>();
        List<String> receiveUserList = fillNotifyRecordList(notify, receiveUserIds, collect);
        NotifyScheduleUtil.createScheduleJob(scheduler, notify, receiveUserList);
        notifyRecordService.saveBatch(collect);
        return insert;
    }

    @Transactional
    @Override
    public boolean update(Notify notify, String receiveUserIds) throws SchedulerException {
        notify.setUpdateTime(new Date());
        notify.setUpdateBy(ShiroUtil.getSessionUid());
        boolean update = updateById(notify);
        notifyRecordService.remove(new QueryWrapper<NotifyRecord>().eq("notifyId", notify.getId()));
        List<NotifyRecord> collect = new ArrayList<>();
        List<String> receiveUserList = fillNotifyRecordList(notify, receiveUserIds, collect);
        if (scheduler.checkExists(NotifyScheduleUtil.getTriggerKey(notify.getId()))) {
            scheduler.deleteJob(NotifyScheduleUtil.getJobKey(notify.getId()));
        }
        NotifyScheduleUtil.createScheduleJob(scheduler, notify, receiveUserList);
        notifyRecordService.saveBatch(collect);
        return update;
    }

    @Override
    public boolean remove(Long id) throws SchedulerException {
        if (scheduler.checkExists(NotifyScheduleUtil.getTriggerKey(id))) {
            scheduler.deleteJob(NotifyScheduleUtil.getJobKey(id));
        }
        return update().set("delFlag", 1).eq("id", id).update();

    }

    @Override
    public boolean batchRemove(Long[] ids) throws SchedulerException {
        for (Long id : ids) {
            if (scheduler.checkExists(NotifyScheduleUtil.getTriggerKey(id))) {
                scheduler.deleteJob(NotifyScheduleUtil.getJobKey(id));
            }
        }
        return update().set("delFlag", 1).in("id", Arrays.asList(ids)).update();
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
        if (StringUtils.isBlank(receiveUserIds)) {
            for (User user : userService.list(new QueryWrapper<>())) {
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
            for (String receiveUserId : split) {
                User user = userService.getById(receiveUserId);
                if (user == null) {
                    continue;
                }
                NotifyRecord notifyRecord = new NotifyRecord();
                notifyRecord.setCreateTime(new Date());
                notifyRecord.setRead(false);
                notifyRecord.setReceiveUserId(receiveUserId);
                notifyRecord.setReceiveUserName(user.getUserName());
                notifyRecord.setNotifyId(notify.getId());
                collect.add(notifyRecord);
            }
        }
        return split;
    }
}
