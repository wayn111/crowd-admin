package com.wayn.quartz.util;

import com.wayn.quartz.domain.Job;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 */
public class ScheduleJobExecution extends AbstractJobExecution {

    @Override
    protected void doExecutor(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
