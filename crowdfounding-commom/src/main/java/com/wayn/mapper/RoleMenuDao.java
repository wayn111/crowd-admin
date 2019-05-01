package com.wayn.mapper;

import com.wayn.domain.Menu;
import com.wayn.domain.RoleMenu;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

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
