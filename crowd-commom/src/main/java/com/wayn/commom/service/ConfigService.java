package com.wayn.commom.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.commom.domain.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 参数配置 服务层
 *
 * @author wayn
 * @date 2020-09-16
 */
public interface ConfigService extends IService<Config> {
    /**
     * 查询参数配置信息
     *
     * @param page 分页对象，自动分页
     * @return 参数配置信息
     */
    Page<Config> selectConfigList(Page<Config> page, Config config);

    /**
     * 根据参数key获取参数value
     *
     * @param key
     * @return
     */
    String getValueByKey(String key);

    void export(Config config, HttpServletResponse response, HttpServletRequest request) throws IOException;

}
