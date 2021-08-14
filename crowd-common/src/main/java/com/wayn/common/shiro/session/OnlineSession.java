package com.wayn.common.shiro.session;

import com.wayn.common.enums.OnlineStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * 在线用户会话属性
 *
 * @author ruoyi
 */
public class OnlineSession extends SimpleSession {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.ON_LINE;

    public String getUserId() {
        return userId;
    }

    public OnlineSession setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OnlineSession setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDeptName() {
        return deptName;
    }

    public OnlineSession setDeptName(String deptName) {
        this.deptName = deptName;
        return this;
    }

    public String getBrowser() {
        return browser;
    }

    public OnlineSession setBrowser(String browser) {
        this.browser = browser;
        return this;
    }

    public String getOs() {
        return os;
    }

    public OnlineSession setOs(String os) {
        this.os = os;
        return this;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public OnlineSession setStatus(OnlineStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
    }

    @Override
    public Object removeAttribute(Object key) {
        return super.removeAttribute(key);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sessionId", super.getId())
                .append("userId", userId)
                .append("username", username)
                .append("deptName", deptName)
                .append("host", super.getHost())
                .append("browser", browser)
                .append("os", os)
                .append("status", status)
                .toString();
    }
}
