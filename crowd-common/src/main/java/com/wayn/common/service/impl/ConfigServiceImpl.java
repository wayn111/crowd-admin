package com.wayn.common.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.common.dao.ConfigDao;
import com.wayn.common.domain.Config;
import com.wayn.common.excel.IExcelExportStylerImpl;
import com.wayn.common.service.ConfigService;
import com.wayn.common.util.ServletUtil;
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
 * 参数配置 服务层实现
 *
 * @author wayn
 * @date 2020-09-16
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, Config> implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    /**
     * 查询参数配置信息
     *
     * @param page
     * @return 参数配置信息
     */
    @Override
    public Page<Config> selectConfigList(Page<Config> page, Config config) {
        return page.setRecords(configDao.selectConfigPageList(page, config));
    }

    @Override
    public String getValueByKey(String key) {
        QueryWrapper<Config> wrapper = new QueryWrapper<>();
        wrapper.eq("configKey", key);
        Config config = getOne(wrapper);
        if (config == null) {
            return null;
        }
        return config.getConfigValue();
    }

    @Override
    public void export(Config config, HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<Config> configs = configDao.selectConfigList(config);
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Config.class, configs);
        // 使用bos获取excl文件大小
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        ServletUtil.setExportResponse(request, response, "参数配置.xls", bos.size());
        // 保存数据
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.close();
        bos.close();
    }

}
