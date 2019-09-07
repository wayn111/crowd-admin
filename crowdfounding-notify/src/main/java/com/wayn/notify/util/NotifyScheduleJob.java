package com.wayn.notify.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wayn.commom.domain.User;
import com.wayn.commom.service.UserOnlineService;
import com.wayn.commom.util.SpringContextUtil;
import com.wayn.notify.domain.Notify;
import com.wayn.notify.service.NotifyService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;


/**
 * 通知发布处理（允许并发执行）
 */
public class NotifyScheduleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        Notify notify = (Notify) mergedJobDataMap.get("notify");
        List<String> receiveUserList = (List<String>) mergedJobDataMap.get("receiveUserList");
        UserOnlineService userOnlineService = SpringContextUtil.getBean(UserOnlineService.class);
        SimpMessagingTemplate simpMessagingTemplate = SpringContextUtil.getBean(SimpMessagingTemplate.class);
        NotifyService notifyService = SpringContextUtil.getBean(NotifyService.class);
        for (User user : userOnlineService.listUser()) {
            for (String userId : receiveUserList) {
                if (userId.equals(user.getId())) {
                    simpMessagingTemplate.convertAndSendToUser(userId, "/queue/notifications", "新消息：" + notify.getTitle());
                }
            }
        }
        notifyService.updateForSet("notifyState = 1", new EntityWrapper<Notify>().eq("id", notify.getId()));
    }
}
