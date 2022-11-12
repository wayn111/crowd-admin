package com.wayn.notify.controller;

import com.wayn.common.base.BaseController;
import com.wayn.common.base.WebSocketReq;
import com.wayn.common.base.WebSocketResp;
import com.wayn.common.shiro.session.OnlineSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * stomp消息路由
 */
@Slf4j
@Controller
public class WsController extends BaseController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SessionDAO sessionDAO;

    @MessageMapping("/message")
    public void message(WebSocketReq webSocketReq) {
        log.info(webSocketReq.toString());
        String connId = webSocketReq.getConnId();
        if (webSocketReq.getType() == 1) {
            String userId = webSocketReq.getUserId();
            WebSocketResp webSocketResp = new WebSocketResp(1, checkUserLogin(userId) ? 1 : 0);
            simpMessagingTemplate.convertAndSendToUser(connId, "/queue/getResponse", webSocketResp);
        }
    }

    private boolean checkUserLogin(String userId) {
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        for (Session activeSession : activeSessions) {
            OnlineSession session = (OnlineSession) activeSession;
            if (session.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
