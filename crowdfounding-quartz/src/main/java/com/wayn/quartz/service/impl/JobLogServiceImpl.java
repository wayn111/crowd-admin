package com.wayn.quartz.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.excel.IExcelExportStylerImpl;
import com.wayn.commom.util.ServletUtil;
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
public class JobLogServiceImpl extends ServiceImpl<JobLogDao, JobLog> implements JobLogService {
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
        return page.setRecords(jobLogDao. selectJobLogList(page,jobLog));
    }

    @Override
    public boolean save(JobLog jobLog) {
        return insert(jobLog);
    }

    @Override
    public boolean update(JobLog jobLog) {
        return updateById(jobLog);
    }

    @Override
    public boolean remove(Long id) {
        return deleteById(id);
    }

    @Override
    public boolean batchRemove(Long[] ids) {
        return deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public void export(JobLog jobLog, HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<JobLog> jobLogs = jobLogDao.selectJobLogList(jobLog);
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, JobLog.class, jobLogs);
        // 使用bos获取excl文件大小
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        ServletUtil.setExportResponse(request, response, "任务日志列表.xls", bos.size());
        //保存数据
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.close();
        bos.close();
    }

}
