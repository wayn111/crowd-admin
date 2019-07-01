package com.wayn.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wayn.domain.Dict;

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

    boolean exists( Dict dict);

    boolean save(Dict dict);

    boolean update(Dict dict);

    boolean remove(Long id);

    boolean batchRemove(Long[] ids);

    /**
     * 查询所有字典类型，根据dictType设置默认选中
     * @param dictType
     * @return
     */
    List<JSONObject> selectDicts(String dictType);

    /**
     * 查询对应字典分类下所有字典数据
     * @param dictType
     * @return
     */
    List<JSONObject> selectDictsValueByType(String dictType);


}
