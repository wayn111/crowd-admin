package com.wayn.quartz.util;

import com.wayn.commom.exception.BusinessException;
import com.wayn.quartz.consts.ScheduleConstants;
import com.wayn.quartz.domain.Job;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务schedule帮助类
 */
public class ScheduleUtil {
    private static final Logger log = LoggerFactory.getLogger(ScheduleUtil.class);

    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId, String jobGroup) {
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId, String jobGroup) {
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId, String jobGroup) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId, jobGroup));
        } catch (SchedulerException e) {
            log.error("获取Cron表达式失败", e);
        }
        return null;
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, Job job) {

        try {
            // 根据是否允许并行，获取Job任务类
            Class<? extends org.quartz.Job> jobClass = job.getConcurrent() == 1 ? ScheduleJobExecution.class : ScheduleJobDisallowConcurrentExecution.class;
            // 构建job信息
            Long jobId = job.getId();
            String jobGroup = job.getJobGroup();

            // 创建作业
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(getJobKey(job.getId(), job.getJobGroup()))
                    .build();

            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(jobId, jobGroup))
                    .withSchedule(cronScheduleBuilder).build();

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, job);
            // 判断是否存在
            if (scheduler.checkExists(getJobKey(jobId, jobGroup))) {
                // 防止创建时存在数据问题 先移除，然后在执行创建操作
                scheduler.deleteJob(getJobKey(jobId, jobGroup));
            }

            scheduler.scheduleJob(jobDetail, trigger);

            // 暂停任务
            if (job.getJobState().equals(ScheduleConstants.Status.PAUSE.getValue())) {
                scheduler.pauseJob(ScheduleUtil.getJobKey(jobId, jobGroup));
            }
        } catch (SchedulerException e) {
            log.error("创建定时任务失败", e);
        }
    }


    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, Job job) {
        try {
            // 参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleConstants.TASK_PROPERTIES, job);
            scheduler.triggerJob(getJobKey(job.getId(), job.getJobGroup()), dataMap);
        } catch (SchedulerException e) {
            log.error("执行定时任务失败", e);
        }
    }


    /**
     * 设置错过触发策略
     */
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(Job job, CronScheduleBuilder cb)
            throws BusinessException {
        switch (job.getMisfirePolicy()) {
            case ScheduleConstants.MISFIRE_DEFAULT:
                return cb;
            case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
                return cb.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
                return cb.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleConstants.MISFIRE_DO_NOTHING:
                return cb.withMisfireHandlingInstructionDoNothing();
            default:
                throw new BusinessException("The task misfire policy '" + job.getMisfirePolicy()
                        + "' cannot be used in cron schedule tasks");
        }
    }
}
