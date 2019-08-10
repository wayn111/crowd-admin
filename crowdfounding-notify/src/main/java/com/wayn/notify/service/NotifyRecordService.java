package com.wayn.notify.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wayn.notify.domain.NotifyRecord;

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
     * @return 通知记录信息
     */
    public Page<NotifyRecord> selectNotifyRecordList(Page<NotifyRecord> page, NotifyRecord notifyRecord);

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
}
