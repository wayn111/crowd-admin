package com.wayn.commom.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wayn.commom.base.BaseEntity;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_log")
public class Log extends BaseEntity<Log> {

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 操作状态 1 正常 -1 失败
     */
    private Integer operState;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 用户
     */
    private String userName;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 日志
     */
    private String operation;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 浏览器信息
     */
    private String agent;

    /**
     * 参数
     */
    private String requestParams;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public Log setRequestParams(String requestParams) {
        this.requestParams = requestParams;
        return this;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Log setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public Integer getOperState() {
        return operState;
    }

    public Log setOperState(Integer operState) {
        this.operState = operState;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public Log setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getAgent() {
        return agent;
    }

    public Log setAgent(String agent) {
        this.agent = agent;
        return this;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", operState=" + operState +
                ", errorMsg='" + errorMsg + '\'' +
                ", userName='" + userName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", operation='" + operation + '\'' +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", agent='" + agent + '\'' +
                ", requestParams='" + requestParams + '\'' +
                "} " + super.toString();
    }
}
