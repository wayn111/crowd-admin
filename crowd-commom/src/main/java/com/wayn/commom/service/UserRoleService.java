package com.wayn.commom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.commom.domain.UserRole;

import java.util.Set;

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
