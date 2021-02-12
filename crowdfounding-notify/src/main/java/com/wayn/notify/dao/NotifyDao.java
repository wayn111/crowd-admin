package com.wayn.notify.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.notify.domain.Notify;

import java.util.List;

/**
 * 通知通告 数据层
 *
 * @author wayn
 * @date 2019-08-10
 */
public interface NotifyDao extends BaseMapper<Notify> {
    /**
     * 查询通知通告信息
     *
     * @param page，自动分页
     * @return 通知通告信息
     */
    List<Notify> selectNotifyList(Page page, Notify notify);

}
