package com.wayn.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.common.domain.OperLog;
import com.wayn.common.domain.vo.EchartVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    OperLog detail(String id);

    List<EchartVO> selectModuleUseStatistic();
}
