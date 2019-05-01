package com.wayn.service;

import com.wayn.domain.UserRole;

import java.util.Set;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface UserRoleService extends IService<UserRole> {

	Set<String> findRolesByUid(String id);

}
