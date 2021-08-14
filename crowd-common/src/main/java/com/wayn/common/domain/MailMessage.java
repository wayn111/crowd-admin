package com.wayn.common.domain;

import com.wayn.common.domain.vo.SendMailVO;

import java.io.Serializable;

public class MailMessage implements Serializable {
    private static final long serialVersionUID = 7005631121530769073L;

    private MailConfig mailConfig;

    private SendMailVO sendMailVO;

    public MailConfig getMailConfig() {
        return mailConfig;
    }

    public MailMessage setMailConfig(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
        return this;
    }

    public SendMailVO getSendMailVO() {
        return sendMailVO;
    }

    public MailMessage setSendMailVO(SendMailVO sendMailVO) {
        this.sendMailVO = sendMailVO;
        return this;
    }

    public MailMessage(MailConfig mailConfig, SendMailVO sendMailVO) {
        this.mailConfig = mailConfig;
        this.sendMailVO = sendMailVO;
    }

    public MailMessage() {
    }
}
