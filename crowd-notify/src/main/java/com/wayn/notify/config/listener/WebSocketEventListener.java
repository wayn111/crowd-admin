package com.wayn.notify.config.listener;

import com.wayn.common.domain.User;
import com.wayn.common.service.UserOnlineService;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * websocket连接监听
 */
@Component
public class WebSocketEventListener {

    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);


    @Autowired
    private UserOnlineService userOnlineService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        if (event.getUser() != null) {
            userOnlineService.put(event.getUser().getName(), event.getUser());
            log.info("Received a new web socket connection, {}", event.getUser());
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) headerAccessor.getSessionAttributes().get(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (principalCollection != null) {
            Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
            User user = (User) primaryPrincipal;
            if (user != null && event.getUser() != null) {
                userOnlineService.remove(event.getUser().getName());
                log.info("User Disconnected : " + user);
            }
        }
    }

}
