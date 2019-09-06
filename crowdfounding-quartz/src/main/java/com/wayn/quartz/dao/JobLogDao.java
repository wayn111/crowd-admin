package com.wayn.quartz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wayn.quartz.domain.JobLog;

import java.util.List;

/**
 * 定时任务调度日志 数据层
 *
 * @author wayn
 * @date 2019-09-04
 */
public interface JobLogDao extends BaseMapper<JobLog>{
    /**
     * 查询定时任务调度日志信息
     *
     * @param page，自动分页
     * @return 定时任务调度日志信息
     */
    public List<JobLog> selectJobLogList(Pagination page, JobLog jobLog);

}