package com.wayn.common.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayn.common.base.BusinessEntity;

import java.io.Serial;
import java.util.StringJoiner;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_user")
public class User extends BusinessEntity<User> {

    @Serial
    private static final long serialVersionUID = 8132056072371500401L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String userName;

    /**
     * 昵称
     */
    @Excel(name = "昵称")
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户状态,1-启用,-1禁用
     */
    @Excel(name = "用户状态", replace = {"启用_1", "禁用_-1"})
    private Integer userState;

    /**
     * 头像
     */
    @Excel(name = "头像", type = 2, width = 40, height = 40, imageType = 1, savePath = "/E:/wayn/upload/excel")
    private String userImg;

    /**
     * 手机
     */
    @Excel(name = "手机号", width = 15)
    private String phone;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", width = 20)
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

    public String getNickName() {
        return nickName;
    }

    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("userName='" + userName + "'")
                .add("nickName='" + nickName + "'")
                .add("password='" + password + "'")
                .add("userState=" + userState)
                .add("userImg='" + userImg + "'")
                .add("phone='" + phone + "'")
                .add("email='" + email + "'")
                .add("deptId=" + deptId)
                .toString();
    }
}
