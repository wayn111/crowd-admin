package com.wayn.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wayn.domain.Log;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface LogService extends IService<Log> {
    Page<Log> listPage(Page<Log> page, Log log);
}
