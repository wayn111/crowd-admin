package com.wayn.notify.controller;

import com.wayn.common.base.BaseController;
import com.wayn.common.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * stomp消息路由
 */
@Slf4j
@Controller
public class WsController extends BaseController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    public void message(Response message) {
        log.info(message.toString());
        simpMessagingTemplate.convertAndSendToUser(message.getMap().get("userId").toString(), "/queue/getResponse", "测试个人通知！");
    }
}
