package com.wayn.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.util.TreeBuilderUtil;
import com.wayn.domain.Menu;
import com.wayn.domain.RoleMenu;
import com.wayn.domain.vo.Tree;
import com.wayn.mapper.MenuDao;
import com.wayn.mapper.RoleMenuDao;
import com.wayn.service.MenuService;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RoleMenuDao roleMenuDao;

	@Override
	public List<String> selectMenuIdsByUid(String id) {
		return menuDao.selectMenuIdsByUid(id);
	}

	@Override
	public List<String> selectResourceByUid(String id) {
		return menuDao.selectResourceByUid(id);
	}

	/**
	 * 获取首页菜单
	 */
	@Cacheable(value = "menuCache", key = "#id + '_selectTreeMenuByUserId'")
	@Override
	public List<Menu> selectTreeMenuByUserId(String id) {
		List<Menu> menus = roleMenuDao.selectRoleMenuIdsByUserId(id);
		List<Menu> treeMenus = selectTreeMenuByMenusAndPid(menus, 0L);
		return treeMenus;
	}

	/**
	 * 将菜单列表封装成菜单树，不包含按钮
	 * @param menus
	 * @param pid 顶级父id
	 * @return
	 */
	public List<Menu> selectTreeMenuByMenusAndPid(List<Menu> menus, Long pid) {
		List<Menu> returnList = new ArrayList<Menu>();
		menus.forEach(menu -> {
			if (pid.equals(menu.getPid())) {
				//只设置菜单类型为 目录 1 或者菜单 2 的记录
				if (menu.getType() < 2) {
					menu.setChildren(selectTreeMenuByMenusAndPid(menus, menu.getId()));
				}
				returnList.add(menu);
			}

		});
		return returnList;
	}

	/**
	 * 获取菜单树，包含按钮
	 */
	@Override
	public Tree<Menu> getTree() {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menus = menuDao.selectList(new EntityWrapper<Menu>());
		menus.forEach(menu -> {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(menu.getId().toString());
			tree.setParentId(menu.getPid().toString());
			tree.setText(menu.getMenuName());
			trees.add(tree);
		});
		return TreeBuilderUtil.<Menu>build(trees);
	}

	/**
	 * 获取菜单树，包含按钮，并根据用户id查询该用户是否包含此菜单，设置菜单是否选中
	 * @param id 用户id
	 */
	@Override
	public Tree<Menu> getTree(String id) {
		List<RoleMenu> list = roleMenuDao.selectList(new EntityWrapper<RoleMenu>().eq("roleId", id));
		List<Long> menuIds = new ArrayList<Long>();
		list.forEach(item -> {
			menuIds.add(item.getMenuId());
		});
		//去掉菜单的父菜单，jstree默认会勾选父菜单
		if (menuIds.size() > 0) {
			List<Menu> list2 = menuDao.selectBatchIds(menuIds);
			List<Long> temp = menuIds;
			for (Menu menu : list2) {
				if (temp.contains(menu.getPid())) {
					menuIds.remove(menu.getPid());
				}
			}
		}
		List<Menu> menus = menuDao.selectList(new EntityWrapper<Menu>());
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		menus.forEach(menu -> {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(menu.getId().toString());
			tree.setParentId(menu.getPid().toString());
			tree.setText(menu.getMenuName());
			Map<String, Object> state = new HashMap<String, Object>();
			Long menuId = menu.getId();
			//设置选中状态
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		});
		return TreeBuilderUtil.build(trees);
	}

	@CacheEvict(value = "menuCache", allEntries = true)
	@Override
	public boolean insert(Menu entity) {
		return super.insert(entity);
	}

	@CacheEvict(value = "menuCache", allEntries = true)
	@Override
	public boolean updateById(Menu entity) {
		return super.updateById(entity);
	}

	@CacheEvict(value = "menuCache", allEntries = true)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

}
