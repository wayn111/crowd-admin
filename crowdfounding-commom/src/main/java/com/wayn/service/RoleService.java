package com.wayn.service;

import com.wayn.commom.exception.BusinessException;
import com.wayn.domain.Role;
import com.wayn.domain.User;
import com.wayn.domain.vo.RoleChecked;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
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

	Page<Role> listPage(Page<Role> page, Map<String, Object> params);
}
