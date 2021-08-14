package com.wayn.common.domain.vo;

import java.io.Serializable;

/**
 * 发送邮件VO对象
 */
public class SendMailVO implements Serializable {
    private static final long serialVersionUID = 3496419936455305502L;
    /**
     * 接收人
     */
    private String receiverUser;

    /**
     * 发送邮箱
     */
    private String sendMail;
    /**
     * 邮件标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;

    public String getReceiverUser() {
        return receiverUser;
    }

    public SendMailVO setReceiverUser(String receiverUser) {
        this.receiverUser = receiverUser;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SendMailVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SendMailVO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSendMail() {
        return sendMail;
    }

    public SendMailVO setSendMail(String sendMail) {
        this.sendMail = sendMail;
        return this;
    }
}
