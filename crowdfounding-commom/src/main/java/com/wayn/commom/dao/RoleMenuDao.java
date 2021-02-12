package com.wayn.commom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayn.commom.domain.Menu;
import com.wayn.commom.domain.RoleMenu;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface RoleMenuDao extends BaseMapper<RoleMenu> {

	List<Menu> selectRoleMenuIdsByUserId(String uid);

	boolean batchSave(List<RoleMenu> list);
}
