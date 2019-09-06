package com.wayn.quartz.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.quartz.dao.JobLogDao;
import com.wayn.quartz.domain.JobLog;
import com.wayn.quartz.service.JobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 定时任务调度日志 服务层实现
 *
 * @author wayn
 * @date 2019-09-04
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogDao, JobLog> implements JobLogService {
    @Autowired
    private JobLogDao jobLogDao;

    /**
     * 查询定时任务调度日志信息
     *
     * @param page
     * @return 定时任务调度日志信息
     */
    @Override
    public Page<JobLog> selectJobLogList(Page<JobLog> page, JobLog jobLog) {
        return page.setRecords(jobLogDao. selectJobLogList(page,jobLog));
    }

    @Override
    public boolean save(JobLog jobLog) {
        return insert(jobLog);
    }

    @Override
    public boolean update(JobLog jobLog) {
        return updateById(jobLog);
    }

    @Override
    public boolean remove(Long id) {
        return deleteById(id);
    }

    @Override
    public boolean batchRemove(Long[] ids) {
        return deleteBatchIds(Arrays.asList(ids));
    }

}
