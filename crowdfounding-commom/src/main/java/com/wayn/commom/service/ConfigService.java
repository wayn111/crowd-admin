package com.wayn.commom.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wayn.commom.domain.Config;
import com.wayn.commom.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public Page<Config> selectConfigList(Page<Config> page, Config config);

    /**
     * 新增{tableComment}
     *
     * @param config 参数配置信息
     * @return 结果
     */
    boolean save(Config config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    boolean update(Config config);

    /**
     * 删除参数配置
     *
     * @param id
     * @return 结果
     */
    boolean remove(Integer id);

    /**
     * 批量删除参数配置
     *
     * @param ids
     * @return 结果
     */
    boolean batchRemove(Integer[] ids);

    /**
     * 根据参数key获取参数value
     *
     * @param key
     * @return
     */
    String getValueByKey(String key);

    void export(Config config, HttpServletResponse response, HttpServletRequest request) throws IOException;

}
