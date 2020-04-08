package com.wayn.commom.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wayn.commom.domain.OperLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    void export(OperLog log, HttpServletResponse response, HttpServletRequest request) throws IOException;
}
