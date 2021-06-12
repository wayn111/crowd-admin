package com.wayn.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.quartz.domain.Job;

import java.util.List;

/**
 * 定时任务调度 数据层
 *
 * @author wayn
 * @date 2019-09-04
 */
public interface JobDao extends BaseMapper<Job> {
    /**
     * 查询定时任务调度信息
     *
     * @param page，自动分页
     * @return 定时任务调度信息
     */
    List<Job> selectJobList(Page<Job> page, Job job);

}
