package com.wayn.mailserver;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MailApplication {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:spring-jms-consumer.xml");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
