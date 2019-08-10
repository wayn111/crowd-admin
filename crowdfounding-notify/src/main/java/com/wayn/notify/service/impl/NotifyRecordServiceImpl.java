package com.wayn.notify.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.notify.dao.NotifyRecordDao;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.service.NotifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 通知记录 服务层实现
 *
 * @author wayn
 * @date 2019-08-10
 */
@Service
public class NotifyRecordServiceImpl extends ServiceImpl<NotifyRecordDao, NotifyRecord> implements NotifyRecordService {
    @Autowired
    private NotifyRecordDao notifyRecordDao;


    @Override
    public Page<NotifyRecord> selectNotifyRecordList(Page<NotifyRecord> page, NotifyRecord notifyRecord) {
        return page.setRecords(notifyRecordDao.selectNotifyRecordList(page, notifyRecord));
    }

    @Override
    public boolean save(NotifyRecord notifyRecord) {
        return insert(notifyRecord);
    }

    @Override
    public boolean update(NotifyRecord notifyRecord) {
        return updateById(notifyRecord);
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
