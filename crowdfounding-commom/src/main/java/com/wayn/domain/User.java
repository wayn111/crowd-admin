package com.wayn.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer _0 = 0;
	public static final Integer _1 = 1;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户状态,1-启用,-1禁用
	 */
	private Integer userState;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 描述
	 */
	private String userDesc;

	/**
	 * 头像
	 */
	private String userImg;

	/**
	 * 部门主键
	 */
	private Long deptId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", userState=" + userState
				+ ", createTime=" + createTime + ", userDesc=" + userDesc + ", userImg=" + userImg + ", deptId="
				+ deptId + "}";
	}
}
