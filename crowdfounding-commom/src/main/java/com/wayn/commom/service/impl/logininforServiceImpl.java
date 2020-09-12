package com.wayn.commom.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.dao.LogininforDao;
import com.wayn.commom.domain.Logininfor;
import com.wayn.commom.excel.IExcelExportStylerImpl;
import com.wayn.commom.service.LogininforService;
import com.wayn.commom.util.FileUtils;
import com.wayn.commom.util.ParameterUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class logininforServiceImpl extends ServiceImpl<LogininforDao, Logininfor> implements LogininforService {

    @Autowired
    private LogininforDao logininforDao;

    @Override
    public Page<Logininfor> listPage(Page<Logininfor> page, Logininfor log) {
        EntityWrapper<Logininfor> wrapper = ParameterUtil.get();
        wrapper.like("loginName", log.getLoginName());
        wrapper.like("ipaddr", log.getIpaddr());
        wrapper.like("status", log.getStatus());
        Page<Logininfor> logPage = selectPage(page, wrapper);
        return logPage;
    }

    @Override
    public void export(Logininfor log, HttpServletResponse response, HttpServletRequest request) throws IOException {
        EntityWrapper<Logininfor> wrapper = ParameterUtil.get();
        wrapper.like("loginName", log.getLoginName());
        wrapper.like("ipaddr", log.getIpaddr());
        wrapper.like("status", log.getStatus());
        List<Logininfor> list = selectList(wrapper);
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
                Logininfor.class, list);
        // 使用bos获取excl文件大小
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Length", bos.size() + "");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, "日志列表.xls"));
        response.setContentType("application/octet-stream;charset=UTF-8");
        //保存数据
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.close();
        bos.close();
    }
}
