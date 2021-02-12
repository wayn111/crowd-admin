package com.wayn.notify.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.domain.NotifyRecordTip;
import com.wayn.notify.domain.vo.NotifyRecordVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通知记录 服务层
 *
 * @author wayn
 * @date 2019-08-10
 */
public interface NotifyRecordService extends IService<NotifyRecord> {
    /**
     * 查询通知记录信息
     *
     * @param page 分页对象，自动分页
     * @param notifyRecord
     * @return 通知记录信息
     */
    public Page<NotifyRecordVO> selectNotifyRecordList(Page<NotifyRecordVO> page, NotifyRecordVO notifyRecord);

    /**
     * 新增{tableComment}
     *
     * @param notifyRecord 通知记录信息
     * @return 结果
     */
    boolean save(NotifyRecord notifyRecord);

    /**
     * 修改通知记录
     *
     * @param notifyRecord 通知记录信息
     * @return 结果
     */
    boolean update(NotifyRecord notifyRecord);

    /**
     * 删除通知记录
     *
     * @param id 通知记录信息
     * @return 结果
     */
    boolean remove(Long id);

    /**
     * 批量删除通知记录
     *
     * @param ids 通知记录信息
     * @return 结果
     */
    boolean batchRemove(Long[] ids);

    NotifyRecordVO selectNotifyByNotifyRecordId(Long id);

    Page<NotifyRecordTip> selectNotifyRecordTipList(Page<NotifyRecordTip> page, String curUserId);

    void export(NotifyRecordVO notifyRecordVO, HttpServletResponse response, HttpServletRequest request) throws IOException;
}
