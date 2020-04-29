package com.wayn.commom.domain.vo;

/**
 * 发送邮件VO对象
 */
public class SendMailVO {
    /**
     * 接收人
     */
    private String receiverUser;
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
}
