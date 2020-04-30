package com.wayn.framework.jms.queue;

import com.wayn.commom.domain.MailConfig;
import com.wayn.commom.domain.MailMessage;
import com.wayn.commom.domain.vo.SendMailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class MailQueueProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination queueTextDestination;

    /**
     * 发送消息
     *
     * @param mailConfig
     * @param mailVO
     */
    public void sendMail(MailConfig mailConfig, SendMailVO mailVO) {
        jmsTemplate.send(queueTextDestination, session -> session.createObjectMessage(new MailMessage(mailConfig, mailVO)));
    }
}
