package com.wayn.commom.service.impl;

import com.wayn.commom.dao.UserRoleDao;
import com.wayn.commom.domain.UserRole;
import com.wayn.commom.service.UserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

	@Override
	public Set<String> findRolesByUid(String id) {
		//复杂连表
		List<UserRole> list = selectList(new EntityWrapper<UserRole>().eq("userId", id).setSqlSelect("distinct roleId")
				.exists(" SELECT u.id FROM sys_user u WHERE u.id = userId ")
				.exists("  SELECT r.id FROM sys_role r WHERE r.id = roleId AND r.roleState = 1 "));
		List<String> list2 = list.stream().map(userRole -> {
			return userRole.getRoleId();
		}).collect(Collectors.toList());
		Set<String> set = new HashSet<String>();
		set.addAll(list2);
		return set;
	}

}
