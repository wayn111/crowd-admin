package com.wayn.quartz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.commom.shiro.util.ShiroUtil;
import com.wayn.quartz.consts.ScheduleConstants;
import com.wayn.quartz.dao.JobDao;
import com.wayn.quartz.domain.Job;
import com.wayn.quartz.service.JobService;
import com.wayn.quartz.util.ScheduleUtil;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

/**
 * 定时任务调度 服务层实现
 *
 * @author wayn
 * @date 2019-09-04
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobDao, Job> implements JobService {
    @Autowired
    private JobDao jobDao;

    @Autowired
    private Scheduler scheduler;

    /**
     * 查询定时任务调度信息
     *
     * @param page
     * @return 定时任务调度信息
     */
    @Override
    public Page<Job> selectJobList(Page<Job> page, Job job) {
        return page.setRecords(jobDao.selectJobList(page, job));
    }

    @Transactional
    @Override
    public boolean save(Job job) {
        job.setCreateTime(new Date());
        job.setCreateBy(ShiroUtil.getSessionUser().getUserName());
        boolean insert = super.save(job);
        ScheduleUtil.createScheduleJob(scheduler, job);
        return insert;
    }

    @Transactional
    @Override
    public boolean update(Job job) throws SchedulerException {
        job.setUpdateTime(new Date());
        job.setUpdateBy(ShiroUtil.getSessionUser().getUserName());
        JobKey jobKey = ScheduleUtil.getJobKey(job.getId(), job.getJobGroup());
        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtil.createScheduleJob(scheduler, job);
        return updateById(job);
    }

    @Transactional
    @Override
    public boolean remove(Long id) throws SchedulerException {
        String jobGroup = getById(id).getJobGroup();
        boolean flag = removeById(id);
        if (flag) {
            scheduler.deleteJob(ScheduleUtil.getJobKey(id, jobGroup));
        }
        return flag;
    }

    @Transactional
    @Override
    public boolean batchRemove(Long[] ids) throws SchedulerException {
        return removeByIds(Arrays.asList(ids));
    }

    @Override
    public boolean changeStatus(Job job) throws SchedulerException {
        if (ScheduleConstants.Status.NORMAL.getValue().equals(job.getJobState())) {
            return resumeJob(job);
        }
        return pauseJob(job);
    }

    private boolean pauseJob(Job job) throws SchedulerException {
        boolean update = updateById(job);
        if (update) {
            scheduler.pauseJob(ScheduleUtil.getJobKey(job.getId(), job.getJobGroup()));
        }
        return update;
    }

    private boolean resumeJob(Job job) throws SchedulerException {
        boolean update = updateById(job);
        if (update) {
            scheduler.resumeJob(ScheduleUtil.getJobKey(job.getId(), job.getJobGroup()));
        }
        return update;
    }

    @Override
    public void run(Long id) throws SchedulerException {
        Job job = getById(id);
        String jobGroup = job.getJobGroup();
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, job);
        scheduler.triggerJob(ScheduleUtil.getJobKey(id, jobGroup), dataMap);
    }
}
