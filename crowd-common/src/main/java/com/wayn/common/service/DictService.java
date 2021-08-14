package com.wayn.common.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.common.domain.Dict;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-06-27
 */
public interface DictService extends IService<Dict> {

    Page<Dict> listPage(Page<Dict> page, Dict dict);

    boolean exists(Dict dict);

    boolean saveDict(Dict dict);

    boolean update(Dict dict);

    boolean remove(Serializable id);

    boolean batchRemove(Serializable[] ids);

    /**
     * 查询所有字典类型，根据dictType设置默认选中
     *
     * @param dictTypeSelected
     * @return
     */
    List<JSONObject> selectDicts(String dictTypeSelected);

    /**
     * 查询对应字典分类下所有字典数据
     *
     * @param dictType
     * @return
     */
    List<JSONObject> selectDictsValueByType(String dictType);

    /**
     * 查询对应字典分类下所有字典数据
     *
     * @param dictType
     * @param value
     * @return
     */
    List<JSONObject> selectDictsValueByType(String dictType, String value);

}
