package com.wayn.service.impl;

import com.wayn.domain.UserRole;
import com.wayn.mapper.UserRoleDao;
import com.wayn.service.UserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		List<UserRole> list = selectList(new EntityWrapper<UserRole>().eq("userId", id));
		Set<String> set = new HashSet<String>();
		list.forEach(data -> {
			set.add(data.getRoleId());
		});
		return set;
	}

}
