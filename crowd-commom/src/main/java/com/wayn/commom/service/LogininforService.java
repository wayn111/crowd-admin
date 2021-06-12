package com.wayn.commom.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.commom.domain.Logininfor;
import com.wayn.commom.domain.vo.EchartVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface LogininforService extends IService<Logininfor> {

    Page<Logininfor> listPage(Page<Logininfor> page, Logininfor log);

    boolean addLog(String username, String status, String message);

    void export(Logininfor log, HttpServletResponse response, HttpServletRequest request) throws IOException;

    List<EchartVO> selectLoginLocationCount();
}
