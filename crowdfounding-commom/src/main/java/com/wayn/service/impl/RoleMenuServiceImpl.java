package com.wayn.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.domain.RoleMenu;
import com.wayn.mapper.RoleMenuDao;
import com.wayn.service.MenuService;
import com.wayn.service.RoleMenuService;

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
		Set<String> set = new HashSet<String>();
		list.forEach(data -> {
			set.add(data);
		});
		return set;
	}

}
