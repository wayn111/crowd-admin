package com.wayn.commom.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        QueryWrapper<Dict> wrapper = ParameterUtil.get();
        Integer type = dict.getType();
        if (type == 2 && StringUtils.isNotEmpty(dict.getDictType())) {
            wrapper.eq("dictType", dict.getDictType());
        }
        wrapper.eq("type", type);
        wrapper.eq("delFlag", "0");
        wrapper.like("name", dict.getName());
        return dictDao.selectPage(page, wrapper);
    }

    @Override
    public boolean exists(Dict dict) {
        //如果是修改字典数据，数据值未改变则通过校验
        if (dict.getId() != null) {
            String value = dict.getValue();
            if (getById(dict.getId()).getValue().equals(value)) {
                return false;
            }
        }
        int count = count(new QueryWrapper<Dict>()
                .eq("value", dict.getValue())
                .eq("type", dict.getType())
                .eq("dictType", dict.getDictType())
                .eq("delFlag", 0));
        return count > 0 ? true : false;
    }

    @CacheEvict(value = "dictCache", allEntries = true)
    @Override
    public boolean saveDict(Dict dict) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        dict.setCreateBy(user.getUserName()).setCreateTime(new Date());
        return super.save(dict);
    }

    @CacheEvict(value = "dictCache", allEntries = true)
    @Override
    public boolean update(Dict dict) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        dict.setUpdateBy(user.getUserName()).setUpdateTime(new Date());
        Dict oldDict = getById(dict.getId());
        return update().set("dictType", dict.getValue())
                .eq("dictType", oldDict.getValue())
                .eq("type", "").update();
    }

    @CacheEvict(value = "dictCache", allEntries = true)
    @Override
    public boolean remove(Serializable id) {
        return update().set("delFlag", 1)
                .eq("id", id).update();
    }

    @CacheEvict(value = "dictCache", allEntries = true)
    @Override
    public boolean batchRemove(Serializable[] ids) {
        return update().set("delFlag", 1)
                .in("id", ids).update();
    }

    @Cacheable(value = "dictCache", key = "#root.method + '_' + #root.args[0]")
    @Override
    public List<JSONObject> selectDicts(String dictTypeSelected) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 1);
        wrapper.eq("delFlag", "0");
        List<Dict> dicts = list(wrapper);
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
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 2)
                .eq("delFlag", "0")
                .eq("dictType", dictType)
                .eq("dictState", 1);
        List<Dict> dictList = list(wrapper);
        return convert2select(dictList);
    }

    public List<JSONObject> selectDictsValueByType(String dictType, String value) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 2)
                .eq("delFlag", "0")
                .eq("dictType", dictType)
                .eq("dictState", 1);
        List<Dict> dictList = list(wrapper);
        return convert2select(dictList, value);
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

    public List<JSONObject> convert2select(List<Dict> dicts, String value) {
        return dicts.stream().map(data -> {
            JSONObject jsonObject = new JSONObject();
            if (data.getValue().equals(value)) {
                jsonObject.put("seleted", true);
            }
            jsonObject.put("id", data.getValue());
            jsonObject.put("text", data.getName());
            return jsonObject;
        }).collect(Collectors.toList());
    }
}
