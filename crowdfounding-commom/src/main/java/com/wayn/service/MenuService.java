package com.wayn.service;

import com.baomidou.mybatisplus.service.IService;
import com.wayn.domain.Menu;
import com.wayn.domain.vo.Tree;

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

    public List<String> selectMenuIdsByUid(String id);

    public List<String> selectResourceByUid(String id);

    public List<Menu> selectTreeMenuByUserId(String id) throws Exception;

    public Tree<Menu> getTree();

    public Tree<Menu> getTree(String roleId);

    public List<Menu> list(Menu menu);
}