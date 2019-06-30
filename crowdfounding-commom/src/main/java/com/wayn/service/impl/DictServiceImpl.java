package com.wayn.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.domain.Dict;
import com.wayn.domain.User;
import com.wayn.mapper.DictDao;
import com.wayn.service.DictService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-06-27
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictDao, Dict> implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public Page<Dict> listPage(Page<Dict> page, Dict dict) {
        EntityWrapper<Dict> wrapper = new EntityWrapper<>();
        wrapper.eq("type", 1);
        wrapper.eq("delFlag", "0");
        wrapper.like("name", dict.getName());
        return selectPage(page, wrapper);
    }

    @Override
    public boolean exists(Dict dict) {
        //如果是修改字典分类，数据值未改变则通过校验
        if (dict.getId() != null) {
            String value = dict.getValue();
            if (selectById(dict.getId()).getValue().equals(value)) {
                return false;
            }
        }
        int count = selectCount(new EntityWrapper<Dict>().eq("value", dict.getValue()));
        return count > 0 ? true : false;
    }

    @Override
    public boolean save(Dict dict) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        dict.setCreateBy(user.getUserName()).setCreateTime(new Date());
        return insert(dict);
    }

    @Override
    public boolean update(Dict dict) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        dict.setUpdateBy(user.getUserName()).setUpdateTime(new Date());
        return updateById(dict);
    }

    @Override
    public boolean remove(Long id) {
        return deleteById(id);
    }

    @Override
    public boolean batchRemove(Long[] ids) {
        return deleteBatchIds(Arrays.asList(ids));
    }
}
