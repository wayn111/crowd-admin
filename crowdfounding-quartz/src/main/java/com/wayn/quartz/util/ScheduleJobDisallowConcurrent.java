package com.wayn.quartz.util;

import com.wayn.quartz.consts.ScheduleConstants;
import com.wayn.quartz.domain.Job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务处理（禁止并发执行）
 */
@DisallowConcurrentExecution
public class ScheduleJobDisallowConcurrent extends QuartzJobBean {

    private static Logger log = LoggerFactory.getLogger(ScheduleJobDisallowConcurrent.class);

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        Job job = (Job) mergedJobDataMap.get(ScheduleConstants.TASK_PROPERTIES);
        try {
            JobInvokeUtil.invokeMethod(job);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("任务执行异常  - ：", e);
        }
    }
}
