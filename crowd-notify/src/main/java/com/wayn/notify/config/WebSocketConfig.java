package com.wayn.notify.config;

import com.wayn.notify.config.handshakehandler.MyPrincipalHandshakeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * websocket启用stomp消息配置
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private MyPrincipalHandshakeHandler myPrincipalHandshakeHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.setErrorHandler(new StompSubProtocolErrorHandler());
        stompEndpointRegistry.addEndpoint("/ws/notify")
                // 配置拦截器，在拦截器中配置用户认证信息
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                // 配置握手处理器，websocket的用户认证信息从握手处理器获得
                .setHandshakeHandler(myPrincipalHandshakeHandler)
                // 配置websocket跨域
                .setAllowedOrigins("*")
                // 启用sockjs
                .withSockJS()
//                .setClientLibraryUrl("https://cdn.jsdelivr.net/npm/sockjs-client@1.3.0/dist/sockjs.min.js")
                // 设置连接时长
                .setDisconnectDelay(30 * 1000);
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setUserDestinationPrefix("/user/");
        registry.enableSimpleBroker("/topic", "/queue");
    }
}
