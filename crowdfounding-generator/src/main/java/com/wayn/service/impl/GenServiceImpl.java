package com.wayn.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.domain.TableInfo;
import com.wayn.mapper.GenDao;
import com.wayn.service.GenService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipOutputStream;

@Service
public class GenServiceImpl implements GenService {
    private static final Logger log = LoggerFactory.getLogger(GenServiceImpl.class);

    @Autowired
    private GenDao genMapper;

    /**
     * 查询ry数据库表信息
     *
     *
     * @param page
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    @Override
    public Page<TableInfo> selectTableList(Page<TableInfo> page, TableInfo tableInfo) {
        Page<TableInfo> tableInfoPage = page.setRecords(genMapper.selectTableList(page, tableInfo));
        return tableInfoPage;
    }

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(tableName, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            generatorCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
    }
}
