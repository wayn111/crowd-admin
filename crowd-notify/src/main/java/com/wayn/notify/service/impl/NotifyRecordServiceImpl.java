package com.wayn.notify.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.common.excel.IExcelExportStylerImpl;
import com.wayn.common.util.DateUtils;
import com.wayn.common.util.ServletUtil;
import com.wayn.notify.dao.NotifyRecordDao;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.domain.NotifyRecordTip;
import com.wayn.notify.domain.vo.NotifyRecordVO;
import com.wayn.notify.service.NotifyRecordService;
import com.wayn.quartz.domain.JobLog;
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
import java.util.Objects;

/**
 * 通知记录 服务层实现
 *
 * @author wayn
 * @date 2019-08-10
 */
@Service
public class NotifyRecordServiceImpl extends ServiceImpl<NotifyRecordDao, NotifyRecord> implements NotifyRecordService, IExcelExportServer {
    @Autowired
    private NotifyRecordDao notifyRecordDao;


    @Override
    public Page<NotifyRecordVO> selectNotifyRecordList(Page<NotifyRecordVO> page, NotifyRecordVO notifyRecord) {
        return page.setRecords(notifyRecordDao.selectNotifyRecordList(page, notifyRecord));
    }

    @Override
    public boolean save(NotifyRecord notifyRecord) {
        return super.save(notifyRecord);
    }

    @Override
    public boolean update(NotifyRecord notifyRecord) {
        return updateById(notifyRecord);
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
    public NotifyRecordVO selectNotifyByNotifyRecordId(Long id) {
        return notifyRecordDao.selectNotifyByNotifyRecordId(id);
    }

    @Override
    public Page<NotifyRecordTip> selectNotifyRecordTipList(Page<NotifyRecordTip> page, String curUserId) {
        List<NotifyRecordTip> notifyRecordTips = notifyRecordDao.selectNotifyRecordTipList(page, curUserId);
        for (NotifyRecordTip notifyRecordTip : notifyRecordTips) {
            notifyRecordTip.setBefore(DateUtils.getTimeBefore(Objects.nonNull(notifyRecordTip.getUpdateTime()) ? notifyRecordTip.getUpdateTime() : notifyRecordTip.getPublishTime()));
        }
        return page.setRecords(notifyRecordTips);
    }

    @Override
    public void export(NotifyRecordVO notifyRecordVO, HttpServletResponse response, HttpServletRequest request) throws IOException {
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        // 保存数据
        try (Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams, NotifyRecordVO.class, this, notifyRecordVO);
             // 使用bos获取excl文件大小
             ByteArrayOutputStream bos = new ByteArrayOutputStream();
             OutputStream os = response.getOutputStream()) {
            workbook.write(bos);
            ServletUtil.setExportResponse(request, response, "我的通知列表.xlsx", bos.size());
            bos.writeTo(os);
        }
    }

    @Override
    public List<Object> selectListForExcelExport(Object queryParams, int pageNum) {
        NotifyRecordVO notifyRecordVO = (NotifyRecordVO) queryParams;
        Page page = new Page<>(pageNum, 5000);
        return notifyRecordDao.selectNotifyRecordList(page, notifyRecordVO);

    }
}
