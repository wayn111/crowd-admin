package com.wayn.notify.controller;

import com.wayn.commom.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * stomp消息路由
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/message")
    public void message(Response message) {
        System.out.println(message);
        simpMessagingTemplate.convertAndSend("/topic/getResponse", Response.success("tip"));
    }
}
