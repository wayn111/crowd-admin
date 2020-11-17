package com.wayn.commom.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wayn.commom.base.BusinessEntity;

import java.util.StringJoiner;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_role")
public class Role extends BusinessEntity<Role> {


    private static final long serialVersionUID = -479636130833732532L;

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 角色名称
     */
    @Excel(name = "角色名")
    private String roleName;

    /**
     * 状态,1启用,-1禁用
     */
    @Excel(name = "角色状态", replace = {"启用_1", "禁用_-1"})
    private Integer roleState;

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

    public Integer getRoleState() {
        return roleState;
    }

    public void setRoleState(Integer roleState) {
        this.roleState = roleState;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Role.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("roleName='" + roleName + "'")
                .add("roleState=" + roleState)
                .toString();
    }
}
