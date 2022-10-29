package com.wayn.notify.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    List<NotifyRecordVO> selectNotifyRecordList(Page<NotifyRecordVO> page, NotifyRecordVO notifyRecord);

    /**
     * 根据通知记录id，查看通知对象
     *
     * @param id
     * @return
     */
    NotifyRecordVO selectNotifyByNotifyRecordId(Long id);

    /**
     * 首页查看当前用户的左上角通知
     * @param page
     * @param curUserId
     * @return
     */
    List<NotifyRecordTip> selectNotifyRecordTipList(Page<NotifyRecordTip> page, String curUserId);
}
