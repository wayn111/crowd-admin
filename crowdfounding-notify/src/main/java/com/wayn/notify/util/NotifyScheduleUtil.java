package com.wayn.notify.util;

import com.wayn.notify.domain.Notify;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NotifyScheduleUtil {

    public static final String TASK_NAME = "NOTIFY_";
    public static final String TASK_GROUP = "NOTIFY_GROUP";
    private static Logger log = LoggerFactory.getLogger(NotifyScheduleUtil.class);

    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(TASK_NAME + jobId, TASK_GROUP);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(TASK_NAME + jobId, TASK_GROUP);
    }


    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, Notify notify, List<String> receiveUserList) {

        try {
            // 构建jobKey信息
            Long jobId = notify.getId();

            // 创建作业
            JobDetail jobDetail = JobBuilder.newJob(NotifyScheduleJob.class)
                    .withIdentity(getJobKey(jobId))
                    .build();


            // 如果不是立即发布，则在指定时间发布通知
            // 按发布时间构建一个新的trigger
            SimpleTrigger trigger;
            if (notify.getNotifyState() != 1) {
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(getTriggerKey(jobId))
                        .startAt(notify.getPublishTime()) // use DateBuilder to create a date in the future
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                        .forJob(jobDetail) // identify job with its JobKey
                        .build();
            } else {
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(getTriggerKey(jobId))
                        .startAt(DateBuilder.futureDate(1000, DateBuilder.IntervalUnit.MILLISECOND)) // use DateBuilder to create a date in the future
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                        .forJob(jobDetail) // identify job with its JobKey
                        .build();
            }

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put("notify", notify);
            jobDetail.getJobDataMap().put("receiveUserList", receiveUserList);
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            log.error("创建定时任务失败", e);
        }
    }
}
