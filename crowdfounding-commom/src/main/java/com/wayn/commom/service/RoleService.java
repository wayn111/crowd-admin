package com.wayn.commom.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.commom.domain.Role;
import com.wayn.commom.domain.vo.RoleChecked;
import com.wayn.commom.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

	boolean update(Role role, String menuIds) throws Exception;

	boolean remove(String roleId) throws BusinessException;

	boolean batchRemove(String[] ids) throws BusinessException;

	List<RoleChecked> listCheckedRolesByUid(String uid);

	Page<Role> listPage(Page<Role> page, Role role);

    void export(Role role, HttpServletResponse response, HttpServletRequest request) throws IOException;
}
