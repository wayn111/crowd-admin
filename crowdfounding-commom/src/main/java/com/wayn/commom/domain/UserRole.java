package com.wayn.commom.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String Id;

    /**
     * 用户主键
     */
    private String userId;

    /**
     * 角色主键
     */
    private String roleId;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserRole.class.getSimpleName() + "[", "]")
                .add("Id='" + Id + "'")
                .add("userId='" + userId + "'")
                .add("roleId='" + roleId + "'")
                .toString();
    }
}

