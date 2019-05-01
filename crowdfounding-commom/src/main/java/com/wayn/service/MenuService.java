package com.wayn.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wayn.domain.Menu;
import com.wayn.domain.vo.Tree;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface MenuService extends IService<Menu> {
	public List<String> selectMenuIdsByUid(String id);

	public List<String> selectResourceByUid(String id);

	public List<Menu> selectTreeMenuByUserId(String id) throws Exception;

	public Tree<Menu> getTree();

	public Tree<Menu> getTree(String roleId);
}