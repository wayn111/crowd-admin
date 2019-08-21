package com.wayn.notify.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.domain.NotifyRecordTip;
import com.wayn.notify.domain.vo.NotifyRecordVO;

import java.util.List;

/**
 * 通知记录 数据层
 *
 * @author wayn
 * @date 2019-08-10
 */
public interface NotifyRecordDao extends BaseMapper<NotifyRecord> {
    /**
     * 查询通知记录信息
     *
     * @param page ，自动分页
     * @param notifyRecord
     * @return 通知记录信息
     */
    List<NotifyRecordVO> selectNotifyRecordList(Pagination page, NotifyRecordVO notifyRecord);

    /**
     * 根据通知记录id，查看通知对象
     *
     * @param id
     * @return
     */
    NotifyRecordVO selectNotifyByNotifyRecordId(Long id);

    List<NotifyRecordTip> selectNotifyRecordTipList(Pagination page, String curUserId);
}