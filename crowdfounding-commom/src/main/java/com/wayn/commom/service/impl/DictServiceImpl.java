package com.wayn.commom.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.dao.DictDao;
import com.wayn.commom.domain.Dict;
import com.wayn.commom.domain.User;
import com.wayn.commom.service.DictService;
import com.wayn.commom.util.ParameterUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        EntityWrapper<Dict> wrapper = ParameterUtil.get();
        Integer type = dict.getType();
        if (type == 2 && StringUtils.isNotEmpty(dict.getDictType())) {
            wrapper.eq("dictType", dict.getDictType());
        }
        wrapper.eq("type", type);
        wrapper.eq("delFlag", "0");
        wrapper.like("name", dict.getName());
        return selectPage(page, wrapper);
    }

    @Override
    public boolean exists(Dict dict) {
        //如果是修改字典数据，数据值未改变则通过校验
        if (dict.getId() != null) {
            String value = dict.getValue();
            if (selectById(dict.getId()).getValue().equals(value)) {
                return false;
            }
        }
        int count = selectCount(new EntityWrapper<Dict>().eq("value", dict.getValue()).eq("type", dict.getType()));
        return count > 0 ? true : false;
    }

    @CacheEvict(value = "dictCache", allEntries = true)
    @Override
    public boolean save(Dict dict) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        dict.setCreateBy(user.getUserName()).setCreateTime(new Date());
        return insert(dict);
    }

    @CacheEvict(value = "dictCache", allEntries = true)
    @Override
    public boolean update(Dict dict) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        dict.setUpdateBy(user.getUserName()).setUpdateTime(new Date());
        Dict oldDict = selectById(dict.getId());
        updateForSet("dictType = '" + dict.getValue() + "'", new EntityWrapper<Dict>().eq("dictType", oldDict.getValue()).eq("type",""));
        return updateById(dict);
    }

    @CacheEvict(value = "dictCache", allEntries = true)
    @Override
    public boolean remove(Serializable id) {
        return deleteById(id);
    }

    @CacheEvict(value = "dictCache", allEntries = true)
    @Override
    public boolean batchRemove(Serializable[] ids) {
        return deleteBatchIds(Arrays.asList(ids));
    }

    @Cacheable(value = "dictCache", key = "#root.method + '_' + #root.args[0]")
    @Override
    public List<JSONObject> selectDicts(String dictTypeSelected) {
        EntityWrapper<Dict> wrapper = new EntityWrapper<>();
        wrapper.eq("type", 1);
        wrapper.eq("delFlag", "0");
        List<Dict> dicts = selectList(wrapper);
        List<JSONObject> objectList = dicts.stream().map(data -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", data.getValue());
            jsonObject.put("text", data.getName());
            if (dictTypeSelected.equals(data.getValue())) {
                jsonObject.put("selected", true);
            }
            return jsonObject;
        }).collect(Collectors.toList());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "");
        jsonObject.put("text", "全部");
        objectList.add(0, jsonObject);
        return objectList;
    }

    @Cacheable(value = "dictCache", key = "#root.method + '_' + #root.args[0]")
    @Override
    public List<JSONObject> selectDictsValueByType(String dictType) {
        EntityWrapper<Dict> wrapper = new EntityWrapper<>();
        wrapper.eq("type", 2)
                .eq("delFlag", "0")
                .eq("delFlag", "0")
                .eq("dictType", dictType)
                .eq("dictState", 1);
        List<Dict> dicts = selectList(wrapper);
        List<JSONObject> objectList = convert2select(dicts);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "");
        jsonObject.put("text", "全部");
        objectList.add(0, jsonObject);
        return objectList;
    }

    @Cacheable(value = "dictCache", key = "#root.method + '_' + #root.args[0]")
    @Override
    public List<JSONObject> selectDictsValueByTypeNoAll(String dictType) {
        EntityWrapper<Dict> wrapper = new EntityWrapper<>();
        wrapper.eq("type", 2)
                .eq("delFlag", "0")
                .eq("delFlag", "0")
                .eq("dictType", dictType)
                .eq("dictState", 1);
        List<Dict> dicts = selectList(wrapper);
        return convert2select(dicts);
    }

    /**
     * 将字典列表转化为select2接受的json格式
     *
     * @param dicts
     * @return
     */
    public List<JSONObject> convert2select(List<Dict> dicts) {
        return dicts.stream().map(data -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", data.getValue());
            jsonObject.put("text", data.getName());
            return jsonObject;
        }).collect(Collectors.toList());
    }
}
