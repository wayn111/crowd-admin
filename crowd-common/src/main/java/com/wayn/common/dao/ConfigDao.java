package com.wayn.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.domain.Config;

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
    List<Config> selectConfigPageList(Page<Config> page, Config config);

    List<Config> selectConfigList(Config config);

}
