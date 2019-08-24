package com.wayn.notify.config.handshakehandler;

import com.wayn.commom.domain.User;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

/**
 * 继承DefaultHandshakeHandler，允许使用request
 */
@Component
public class MyPrincipalHandshakeHandler extends DefaultHandshakeHandler {
    private static final Logger log = LoggerFactory.getLogger(MyPrincipalHandshakeHandler.class);

   /* @Lazy
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;*/

    /**
     * 重写determineUser用于填写用户认证信息
     *
     * @param request
     * @param wsHandler
     * @param attributes
     * @return
     */
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Object object = attributes.get(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (Objects.isNull(object)) {
            log.error("未登录系统，禁止登录websocket!");
//            simpMessagingTemplate.convertAndSend("/topic/judgeUserAuth", "您的登陆信息以过期，请重新登录!");
            return null;
        }
        SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) object;
        User user = (User) simplePrincipalCollection.getPrimaryPrincipal();
        return user::getId;
    }
}