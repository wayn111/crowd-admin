package com.wayn.quartz.manager.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * 监听器，每次新建启动，中断后恢复都需要重新添加监听，比较麻烦，不推荐使用
 */
public class MySchedulerListener extends JobListenerSupport {

    @Override
    public String getName() {
        return "MySchedulerListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("jobToBeExecuted");
        System.out.println("Job : " + jobName + " is going to start...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("jobWasExecuted");

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("Job : " + jobName + " is finished...");

        if (jobException != null && !jobException.getMessage().equals("")) {
            System.out.println("Exception thrown by: " + jobName
                    + " Exception: " + jobException.getMessage());
        }
    }
}