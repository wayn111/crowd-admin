package com.wayn.commom.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wayn.commom.domain.Config;

import java.util.List;

/**
 * 参数配置 数据层
 *
 * @author wayn
 * @date 2020-09-16
 */
public interface ConfigDao extends BaseMapper<Config> {
    /**
     * 查询参数配置信息
     *
     * @param page，自动分页
     * @return 参数配置信息
     */
    List<Config> selectConfigPageList(Pagination page, Config config);

    List<Config> selectConfigList(Config config);

}
