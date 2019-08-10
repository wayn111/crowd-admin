package com.wayn.notify.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wayn.notify.domain.NotifyRecord;

import java.util.List;

/**
 * 通知记录 数据层
 *
 * @author wayn
 * @date 2019-08-10
 */
public interface NotifyRecordDao extends BaseMapper<NotifyRecord>{
    /**
     * 查询通知记录信息
     *
     * @param page，自动分页
     * @return 通知记录信息
     */
    public List<NotifyRecord> selectNotifyRecordList(Pagination page, NotifyRecord notifyRecord);

}