package com.wayn.common.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayn.common.base.BaseEntity;

import java.util.Date;
import java.util.StringJoiner;

/**
 * <p>
 * 系统访问记录表
 * </p>
 *
 * @author wayn
 * @since 2020-09-12
 */
@TableName("sys_logininfor")
public class Logininfor extends BaseEntity {

    private static final long serialVersionUID = -7795325595318436879L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long infoId;

    /**
     * 用户账号
     */
    @Excel(name = "用户账号", width = 20)
    private String loginName;

    /**
     * 登录状态 1成功 -1失败
     */
    @Excel(name = "登录状态", replace = {"成功_1", "失败_-1"})
    private String status;

    /**
     * 登录IP地址
     */
    @Excel(name = "登录IP地址", width = 20)
    private String ipaddr;

    /**
     * 登录地点
     */
    @Excel(name = "登录地点", width = 20)
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Excel(name = "浏览器类型", width = 20)
    private String browser;

    /**
     * 操作系统
     */
    @Excel(name = "操作系统", width = 20)
    private String os;

    /**
     * 提示消息
     */
    @Excel(name = "提示消息", width = 20)
    private String msg;

    /**
     * 提示消息
     */
    @Excel(name = "登陆时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Logininfor.class.getSimpleName() + "[", "]")
                .add("infoId=" + infoId)
                .add("loginName='" + loginName + "'")
                .add("status='" + status + "'")
                .add("ipaddr='" + ipaddr + "'")
                .add("loginLocation='" + loginLocation + "'")
                .add("browser='" + browser + "'")
                .add("os='" + os + "'")
                .add("msg='" + msg + "'")
                .add("loginTime='" + loginTime + "'")
                .toString();
    }
}
