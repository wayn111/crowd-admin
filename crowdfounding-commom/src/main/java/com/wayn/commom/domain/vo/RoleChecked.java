package com.wayn.commom.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户管理，配置该用户所拥有得角色选择框
 * @author wayn
 *
 */
public class RoleChecked implements Serializable {

	private static final long serialVersionUID = 4921874281517667471L;
	private String id;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String roleDesc;

	/**
	 * 状态,1-启用,-1禁用
	 */
	private Integer roleState;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * role多选框，是否选中标志
	 */
	private Boolean checked = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getRoleState() {
		return roleState;
	}

	public void setRoleState(Integer roleState) {
		this.roleState = roleState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "RoleChecked [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleState="
				+ roleState + ", createTime=" + createTime + ", checked=" + checked + "]";
	}

}
