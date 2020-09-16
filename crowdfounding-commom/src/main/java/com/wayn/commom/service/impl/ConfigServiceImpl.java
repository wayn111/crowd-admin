package com.wayn.commom.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.dao.ConfigDao;
import com.wayn.commom.domain.Config;
import com.wayn.commom.domain.User;
import com.wayn.commom.excel.IExcelExportStylerImpl;
import com.wayn.commom.service.ConfigService;
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
    public boolean save(Config config) {
        return insert(config);
    }

    @Override
    public boolean update(Config config) {
        return updateById(config);
    }

    @Override
    public boolean remove(Integer id) {
        return deleteById(id);
    }

    @Override
    public boolean batchRemove(Integer[] ids) {
        return deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public String getValueByKey(String key) {
        EntityWrapper<Config> wrapper = new EntityWrapper<>();
        wrapper.eq("configKey", key);
        Config config = selectOne(wrapper);
        if (config != null) {
            return config.getConfigValue();
        }
        return null;
    }

    @Override
    public void export(Config config, HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<Config> configs = configDao.selectConfigList(config);
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
                Config.class, configs);
        // 使用bos获取excl文件大小
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Length", bos.size() + "");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, "用户列表.xls"));
        response.setContentType("application/octet-stream;charset=UTF-8");
        //保存数据
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.close();
        bos.close();
    }

}
