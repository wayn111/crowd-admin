package com.wayn.notify.config.handshakehandler;

import com.wayn.common.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 继承DefaultHandshakeHandler，允许使用request
 */
@Slf4j
@Component
public class MyPrincipalHandshakeHandler extends DefaultHandshakeHandler {


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
            return new ObjectPrincipal(UUID.randomUUID().toString());
        }
        SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) object;
        User user = (User) simplePrincipalCollection.getPrimaryPrincipal();
        return new ObjectPrincipal(user.getId());
    }


    private static class ObjectPrincipal implements java.security.Principal {
        private Object object = null;

        public ObjectPrincipal(Object object) {
            this.object = object;
        }

        public Object getObject() {
            return object;
        }

        public String getName() {
            return getObject().toString();
        }

        public int hashCode() {
            return object.hashCode();
        }

        public boolean equals(Object o) {
            if (o instanceof ObjectPrincipal) {
                ObjectPrincipal op = (ObjectPrincipal) o;
                return getObject().equals(op.getObject());
            }
            return false;
        }

        public String toString() {
            return object.toString();
        }
    }

}
