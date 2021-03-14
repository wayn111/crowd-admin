package com.wayn.quartz.util;

import com.wayn.quartz.consts.ScheduleConstants;
import com.wayn.quartz.domain.JobLog;
import com.wayn.quartz.service.JobLogService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;

public abstract class AbstractJobExecution implements Job {

    private static final Logger logger = LoggerFactory.getLogger(AbstractJobExecution.class);

    private static final ThreadLocal<Long> threadLocal = ThreadLocal.withInitial(() -> 0L);

    @Autowired
    private JobLogService jobLogService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        com.wayn.quartz.domain.Job job = (com.wayn.quartz.domain.Job) mergedJobDataMap.get(ScheduleConstants.TASK_PROPERTIES);
        before(context, job);
        try {
            doExecutor(context, job);
            after(context, job, null);
        } catch (Exception e) {
            logger.error("任务执行异常  - ：", e);
            after(context, job, null);
        }

    }

    protected void before(JobExecutionContext context, com.wayn.quartz.domain.Job job) {
        long startTime = Instant.now().toEpochMilli();
        threadLocal.set(startTime);
    }

    protected void after(JobExecutionContext context, com.wayn.quartz.domain.Job job, Exception e) {
        long startTime = threadLocal.get();
        threadLocal.remove();
        long endTime = Instant.now().toEpochMilli();

        JobLog jobLog = new JobLog();
        jobLog.setCreateTime(new Date());
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setInvokeTarget(job.getInvokeTarget());
        jobLog.setJobState(1);
        jobLog.setJobMessage(jobLog.getJobName() + "总共耗时：" + (endTime - startTime) + "毫秒");
        jobLogService.save(jobLog);
    }

    protected abstract void doExecutor(JobExecutionContext context, com.wayn.quartz.domain.Job job) throws Exception;

}
