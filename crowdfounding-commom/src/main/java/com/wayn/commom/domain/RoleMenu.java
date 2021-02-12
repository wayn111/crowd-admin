package com.wayn.commom.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;

	/**
	 * 角色主键
	 */
	private String roleId;

	/**
	 * 菜单主键
	 */
	private Long menuId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", RoleMenu.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("roleId='" + roleId + "'")
				.add("menuId=" + menuId)
				.toString();
	}
}
