package com.wayn.commom.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wayn.commom.base.BaseEntity;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_user")
public class User extends BaseEntity<User> {

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
	 * 头像
	 */
	private String userImg;

	/**
	 * 手机
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

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

	public String getPhone() {
		return phone;
	}

	public User setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", userState=" + userState +
				", userImg='" + userImg + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", deptId=" + deptId +
				"} " + super.toString();
	}
}
