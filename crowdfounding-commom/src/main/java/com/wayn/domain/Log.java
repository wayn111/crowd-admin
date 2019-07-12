package com.wayn.domain;

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
     * 地址
     */
    private String url;

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

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", operation='" + operation + '\'' +
                ", url='" + url + '\'' +
                ", requestParams='" + requestParams + '\'' +
                "} " + super.toString();
    }
}
