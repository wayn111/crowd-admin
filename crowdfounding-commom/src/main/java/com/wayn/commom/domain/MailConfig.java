package com.wayn.commom.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@TableName("sys_mail_config")
public class MailConfig implements Serializable {

    private static final long serialVersionUID = -8825288678724602467L;
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 邮件服务器SMTP地址 */
    @NotBlank
    private String host;

    /** 邮件服务器SMTP端口 */
    @NotBlank
    private String port;

    @NotBlank
    private String pass;

    /** 发件者用户名，默认为发件人邮箱前缀 */
    @NotBlank
    private String fromUser;

    /**
     * 用户发送状态,1-启用,-1禁用
     */
    private Integer userSendState;

    public Long getId() {
        return id;
    }

    public MailConfig setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHost() {
        return host;
    }

    public MailConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPort() {
        return port;
    }

    public MailConfig setPort(String port) {
        this.port = port;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public MailConfig setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public Integer getUserSendState() {
        return userSendState;
    }

    public MailConfig setUserSendState(Integer userSendState) {
        this.userSendState = userSendState;
        return this;
    }

    public String getFromUser() {
        return fromUser;
    }

    public MailConfig setFromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }
}
