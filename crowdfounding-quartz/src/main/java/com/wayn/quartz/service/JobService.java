package com.wayn.quartz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.quartz.domain.Job;
import org.quartz.SchedulerException;

/**
 * 定时任务调度 服务层
 *
 * @author wayn
 * @date 2019-09-04
 */
public interface JobService extends IService<Job> {
    /**
     * 查询定时任务调度信息
     *
     * @param page 分页对象，自动分页
     * @return 定时任务调度信息
     */
    public Page<Job> selectJobList(Page<Job> page, Job job);

    /**
     * 新增{tableComment}
     *
     * @param job 定时任务调度信息
     * @return 结果
     */
    boolean save(Job job);

    /**
     * 修改定时任务调度
     *
     * @param job 定时任务调度信息
     * @return 结果
     */
    boolean update(Job job) throws SchedulerException;

    /**
     * 删除定时任务调度
     *
     * @param id
     * @return 结果
     */
    boolean remove(Long id) throws SchedulerException;

    /**
     * 批量删除定时任务调度
     *
     * @param ids
     * @return 结果
     */
    boolean batchRemove(Long[] ids) throws SchedulerException;

    boolean changeStatus(Job job) throws SchedulerException;

    void run(Long id) throws SchedulerException;
}
