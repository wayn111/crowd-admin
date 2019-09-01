package com.wayn.commom.shiro.session;

import com.wayn.commom.enums.OnlineStatus;
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
     * 登录IP地址
     */
    private String host;

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
    private OnlineStatus status = OnlineStatus.on_line;

    /**
     * 属性是否改变 优化session数据同步
     */
    private transient boolean attributeChanged = false;

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
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

    public String getUserId() {
        return userId;
    }

    public OnlineSession setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    public void markAttributeChanged() {
        this.attributeChanged = true;
    }

    public void resetAttributeChanged() {
        this.attributeChanged = false;
    }

    public boolean isAttributeChanged() {
        return attributeChanged;
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
    }

    @Override
    public Object removeAttribute(Object key) {
        return super.removeAttribute(key);
    }

    public String getUsername() {
        return username;
    }

    public OnlineSession setUsername(String username) {
        this.username = username;
        return this;
    }

    public OnlineSession setAttributeChanged(boolean attributeChanged) {
        this.attributeChanged = attributeChanged;
        return this;
    }

    @Override
    public String toString() {
        return "OnlineSession{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", host='" + host + '\'' +
                ", browser='" + browser + '\'' +
                ", os='" + os + '\'' +
                ", status=" + status +
                ", attributeChanged=" + attributeChanged +
                "} super = " + super.getId() + "---" + super.getAttributes();
    }
}
