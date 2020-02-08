package com.wayn.commom.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wayn.commom.domain.OperLog;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface LogService extends IService<OperLog> {
    Page<OperLog> listPage(Page<OperLog> page, OperLog log);
}
