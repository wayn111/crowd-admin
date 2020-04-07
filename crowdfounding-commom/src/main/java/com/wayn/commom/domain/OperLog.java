package com.wayn.commom.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wayn.commom.base.BaseEntity;

import java.util.StringJoiner;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_oper_log")
public class OperLog extends BaseEntity<OperLog> {

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
     * 操作类型
     */
    private String operation;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 浏览器信息
     */
    private String agent;


    /**
     * 执行时间
     */
    private Long executeTime;

    /**
     * 参数
     */
    private String requestParams;

    /**
     * 请求类型
     */
    private String requestMethod;

    /**
     * 请求响应
     */
    private String requestResponse;

    public String getId() {
        return id;
    }

    public OperLog setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getOperState() {
        return operState;
    }

    public OperLog setOperState(Integer operState) {
        this.operState = operState;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public OperLog setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public OperLog setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getModuleName() {
        return moduleName;
    }

    public OperLog setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public OperLog setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public OperLog setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public OperLog setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getAgent() {
        return agent;
    }

    public OperLog setAgent(String agent) {
        this.agent = agent;
        return this;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public OperLog setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
        return this;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public OperLog setRequestParams(String requestParams) {
        this.requestParams = requestParams;
        return this;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public OperLog setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public String getRequestResponse() {
        return requestResponse;
    }

    public OperLog setRequestResponse(String requestResponse) {
        this.requestResponse = requestResponse;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public OperLog setMethod(String method) {
        this.method = method;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OperLog.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("operState=" + operState)
                .add("errorMsg='" + errorMsg + "'")
                .add("userName='" + userName + "'")
                .add("moduleName='" + moduleName + "'")
                .add("operation='" + operation + "'")
                .add("url='" + url + "'")
                .add("method='" + method + "'")
                .add("ip='" + ip + "'")
                .add("agent='" + agent + "'")
                .add("executeTime=" + executeTime)
                .add("requestParams='" + requestParams + "'")
                .add("requestMethod='" + requestMethod + "'")
                .add("requestResponse='" + requestResponse + "'")
                .toString();
    }
}
