package com.wayn.framework.jms.queue;

import org.springframework.stereotype.Component;

@Component
public class MailQueueProducer {
  /*  @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination queueTextDestination;

    *//**
     * 发送消息
     *
     * @param mailConfig
     * @param mailVO
     *//*
    public void sendMail(MailConfig mailConfig, SendMailVO mailVO) {
        jmsTemplate.send(queueTextDestination, session -> session.createObjectMessage(new MailMessage(mailConfig, mailVO)));
    }*/
}
