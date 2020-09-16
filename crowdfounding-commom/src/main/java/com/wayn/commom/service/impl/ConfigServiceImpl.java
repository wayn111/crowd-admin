package com.wayn.commom.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.dao.ConfigDao;
import com.wayn.commom.domain.Config;
import com.wayn.commom.domain.User;
import com.wayn.commom.service.ConfigService;
import com.wayn.commom.util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
        return page.setRecords(configDao.selectConfigList(page, config));
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

}
