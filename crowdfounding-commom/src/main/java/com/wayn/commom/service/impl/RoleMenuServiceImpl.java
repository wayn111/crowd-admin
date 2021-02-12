package com.wayn.commom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.commom.dao.RoleMenuDao;
import com.wayn.commom.domain.RoleMenu;
import com.wayn.commom.service.MenuService;
import com.wayn.commom.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

	@Autowired
	private MenuService mynuService;

	@Override
	public Set<String> findMenusByUid(String id) {
		List<String> list = mynuService.selectResourceByUid(id);
		Set<String> set = new HashSet<>();
		set.addAll(list);
		return set;
	}

}
