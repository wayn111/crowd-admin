package com.wayn.service;

import com.wayn.commom.exception.BusinessException;
import com.wayn.domain.Role;
import com.wayn.domain.vo.RoleChecked;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface RoleService extends IService<Role> {

	boolean save(Role role, String menuIds);

	boolean update(Role role, String menuIds);

	boolean remove(String roleId) throws BusinessException;

	boolean batchRemove(String[] ids) throws BusinessException;

	List<RoleChecked> listCheckedRolesByUid(String uid);
}
