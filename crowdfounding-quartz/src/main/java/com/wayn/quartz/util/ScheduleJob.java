package com.wayn.quartz.util;

import com.wayn.commom.util.SpringContextUtil;
import com.wayn.quartz.consts.ScheduleConstants;
import com.wayn.quartz.domain.Job;
import com.wayn.quartz.domain.JobLog;
import com.wayn.quartz.service.JobLogService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 定时任务处理（允许并发执行）
 */
public class ScheduleJob extends QuartzJobBean {

    private static Logger log = LoggerFactory.getLogger(ScheduleJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        Job job = (Job) mergedJobDataMap.get(ScheduleConstants.TASK_PROPERTIES);
        JobLog jobLog = new JobLog();
        jobLog.setCreateTime(new Date());
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setInvokeTarget(job.getInvokeTarget());
        jobLog.setJobState(1);
        try {
            long begin = System.currentTimeMillis();
            JobInvokeUtil.invokeMethod(job);
            long end = System.currentTimeMillis();
            jobLog.setJobMessage(jobLog.getJobName() + "总共耗时：" + (end - begin) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
            jobLog.setJobState(-1);
            jobLog.setExceptionInfo(e.getMessage());
            log.error("任务执行异常  - ：", e);
        }
        JobLogService jobLogService = SpringContextUtil.getBean(JobLogService.class);
        jobLogService.insert(jobLog);
    }
}
