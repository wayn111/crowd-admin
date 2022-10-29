package com.wayn.quartz.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.common.excel.IExcelExportStylerImpl;
import com.wayn.common.util.ServletUtil;
import com.wayn.quartz.dao.JobLogDao;
import com.wayn.quartz.domain.JobLog;
import com.wayn.quartz.service.JobLogService;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 定时任务调度日志 服务层实现
 *
 * @author wayn
 * @date 2019-09-04
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogDao, JobLog> implements JobLogService, IExcelExportServer {
    @Autowired
    private JobLogDao jobLogDao;

    /**
     * 查询定时任务调度日志信息
     *
     * @param page
     * @return 定时任务调度日志信息
     */
    @Override
    public Page<JobLog> selectJobLogList(Page<JobLog> page, JobLog jobLog) {
        return page.setRecords(jobLogDao.selectJobLogList(page, jobLog));
    }

    @Override
    public boolean save(JobLog jobLog) {
        return super.save(jobLog);
    }

    @Override
    public boolean update(JobLog jobLog) {
        return updateById(jobLog);
    }

    @Override
    public boolean remove(Long id) {
        return removeById(id);
    }

    @Override
    public boolean batchRemove(Long[] ids) {
        return removeByIds(Arrays.asList(ids));
    }

    @Override
    public void export(JobLog jobLog, HttpServletResponse response, HttpServletRequest request) throws IOException {
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        try (Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams, JobLog.class, this, jobLog);
             // 使用bos获取excl文件大小
             ByteArrayOutputStream bos = new ByteArrayOutputStream();
             OutputStream os = response.getOutputStream()) {
            workbook.write(bos);
            ServletUtil.setExportResponse(request, response, "任务日志列表.xlsx", bos.size());
            // 保存数据
            bos.writeTo(os);
        }
    }

    @Override
    public List<Object> selectListForExcelExport(Object queryParams, int pageNum) {
        JobLog jobLog = (JobLog) queryParams;
        Page page = new Page<>(pageNum, 5000);
        return jobLogDao.selectJobLogList(page, jobLog);
    }
}
