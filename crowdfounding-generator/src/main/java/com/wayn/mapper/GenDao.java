package com.wayn.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wayn.domain.ColumnInfo;
import com.wayn.domain.TableInfo;

import java.util.List;

/**
 * 代码生成Mapper类
 */
public interface GenDao {
    /**
     * 查询ry数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    public List<TableInfo> selectTableList(Pagination page, TableInfo tableInfo);

    /**
     * 根据表名称查询信息
     *
     * @param tableName 表名称
     * @return 表信息
     */
    public TableInfo selectTableByName(String tableName);

    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    public List<ColumnInfo> selectTableColumnsByName(String tableName);

}
