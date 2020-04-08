package com.wayn.quartz.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wayn.quartz.domain.JobLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定时任务调度日志 服务层
 *
 * @author wayn
 * @date 2019-09-04
 */
public interface JobLogService extends IService<JobLog> {
    /**
     * 查询定时任务调度日志信息
     *
     * @param page 分页对象，自动分页
     * @return 定时任务调度日志信息
     */
    public Page<JobLog> selectJobLogList(Page<JobLog> page, JobLog jobLog);

    /**
     * 新增{tableComment}
     *
     * @param jobLog 定时任务调度日志信息
     * @return 结果
     */
    boolean save(JobLog jobLog);

    /**
     * 修改定时任务调度日志
     *
     * @param jobLog 定时任务调度日志信息
     * @return 结果
     */
    boolean update(JobLog jobLog);

    /**
     * 删除定时任务调度日志
     *
     * @param id
     * @return 结果
     */
    boolean remove(Long id);

    /**
     * 批量删除定时任务调度日志
     *
     * @param ids
     * @return 结果
     */
    boolean batchRemove(Long[] ids);

    void export(JobLog jobLog, HttpServletResponse response, HttpServletRequest request) throws IOException;
}
