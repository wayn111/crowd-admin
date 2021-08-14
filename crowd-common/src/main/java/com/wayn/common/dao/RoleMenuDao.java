package com.wayn.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayn.common.domain.Menu;
import com.wayn.common.domain.RoleMenu;

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
