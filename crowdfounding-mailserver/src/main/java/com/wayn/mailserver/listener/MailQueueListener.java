package com.wayn.mailserver.listener;

import com.wayn.commom.domain.MailMessage;
import com.wayn.commom.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Locale;

/**
 * 邮件发送监听
 */
public class MailQueueListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(MailQueueListener.class);

    private TemplateEngine templateEngine;

    public MailQueueListener(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            MailMessage mailMessage = (MailMessage) objectMessage.getObject();
            Context context = new Context(Locale.CHINESE);
            context.setVariable("name", mailMessage.getSendMailVO().getReceiverUser());
            context.setVariable("content", mailMessage.getSendMailVO().getContent());
            String mail = templateEngine.process("mail", context);
            mailMessage.getSendMailVO().setContent(mail);
            MailUtil.sendMail(mailMessage.getMailConfig(), mailMessage.getSendMailVO());
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
