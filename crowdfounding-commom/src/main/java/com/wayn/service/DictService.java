package com.wayn.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wayn.domain.Dict;

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
}
