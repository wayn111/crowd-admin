package com.wayn.notify.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.notify.dao.NotifyDao;
import com.wayn.notify.domain.Notify;
import com.wayn.notify.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 通知通告 服务层实现
 *
 * @author wayn
 * @date 2019-08-10
 */
@Service
public class NotifyServiceImpl extends ServiceImpl<NotifyDao, Notify> implements NotifyService {
    @Autowired
    private NotifyDao notifyDao;

    /**
     * 查询通知通告信息
     *
     * @param page
     * @return 通知通告信息
     */
    @Override
    public Page<Notify> selectNotifyList(Page<Notify> page, Notify notify) {
        return page.setRecords(notifyDao.selectNotifyList(page, notify));
    }

    @Override
    public boolean save(Notify notify) {
        return insert(notify);
    }

    @Override
    public boolean update(Notify notify) {
        return updateById(notify);
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
