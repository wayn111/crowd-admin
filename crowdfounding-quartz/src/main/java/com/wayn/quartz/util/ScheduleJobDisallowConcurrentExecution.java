package com.wayn.quartz.util;

import com.wayn.quartz.domain.Job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 */
@DisallowConcurrentExecution
public class ScheduleJobDisallowConcurrentExecution extends AbstractJobExecution {

    @Override
    protected void doExecutor(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
