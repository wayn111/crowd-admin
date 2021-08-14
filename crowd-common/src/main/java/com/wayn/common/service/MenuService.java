package com.wayn.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.common.domain.Menu;
import com.wayn.common.domain.vo.MenuVO;
import com.wayn.common.domain.vo.Tree;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface MenuService extends IService<Menu> {
    boolean save(Menu menu);

    boolean update(Menu menu);

    boolean remove(Long id);

    List<String> selectMenuIdsByUid(String id);

    List<String> selectResourceByUid(String id);

    List<Menu> selectTreeMenuByUserId(String id) throws Exception;

    Tree<Menu> getTree();

    Tree<Menu> getTree(String roleId);

    List<Menu> list(MenuVO menu);
}
