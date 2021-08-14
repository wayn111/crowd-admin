package com.wayn.common.domain.vo;

import java.util.Date;
import java.util.StringJoiner;

/**
 * userVO
 */
public class UserVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

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

    /**
     * 部门名称
     */
    private String deptName;

    private String remarks;

    private Date createTime;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserVO.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("userName='" + userName + "'")
                .add("nickName='" + nickName + "'")
                .add("password='" + password + "'")
                .add("userState=" + userState)
                .add("userImg='" + userImg + "'")
                .add("phone='" + phone + "'")
                .add("email='" + email + "'")
                .add("deptId=" + deptId)
                .add("deptName='" + deptName + "'")
                .add("remarks='" + remarks + "'")
                .add("createTime=" + createTime)
                .toString();
    }
}
