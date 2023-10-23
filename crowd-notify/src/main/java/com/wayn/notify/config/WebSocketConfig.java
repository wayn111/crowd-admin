package com.wayn.notify.config;

import com.wayn.common.base.WebSocketResp;
import com.wayn.common.domain.User;
import com.wayn.common.domain.dto.WsUserPrincipal;
import com.wayn.common.exception.BusinessException;
import com.wayn.common.shiro.util.ShiroUtil;
import com.wayn.common.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * websocket启用stomp消息配置
 */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.setErrorHandler(new StompSubProtocolErrorHandler());
        stompEndpointRegistry.addEndpoint("/ws/notify")
                // 配置拦截器，在拦截器中配置用户认证信息
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                // 配置握手处理器，websocket的用户认证信息从握手处理器获得
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        User sessionUser = ShiroUtil.getSessionUser();
                        if (Objects.isNull(sessionUser)) {
                            log.error("未登录系统，禁止登录websocket!");
                            return new WsUserPrincipal(UUID.randomUUID().toString(), "unauth");
                        }
                        return new WsUserPrincipal(sessionUser.getId(), sessionUser.getId());
                    }
                })
                // .addInterceptors(new HandshakeInterceptor() {
                //     @Override
                //     public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                //         ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
                //         HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
                //         String login = httpServletRequest.getParameter("login");
                //         String passcode = httpServletRequest.getParameter("passcode");
                //         return true;
                //     }
                //
                //     @Override
                //     public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
                //
                //     }
                // })
                // 配置websocket跨域
                .setAllowedOriginPatterns("*")
                // 启用sockjs
                .withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setUserDestinationPrefix("/user/");
        registry.enableSimpleBroker("/topic", "/queue");
    }


    // @Override
    // public void configureClientInboundChannel(ChannelRegistration registration) {
    //     registration.interceptors(new ChannelInterceptor() {
    //         @Override
    //         public Message<?> preSend(Message<?> message, MessageChannel channel) {
    //             StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
    //             if (StompCommand.CONNECT.equals(accessor.getCommand())) {
    //                 accessor.setUser(new WsUserPrincipal("", ""));
    //             }
    //             return message;
    //         }
    //     });
    // }
}
