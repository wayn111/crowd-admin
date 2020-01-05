package com.wayn.notify.config;

import com.wayn.notify.config.handshakehandler.MyPrincipalHandshakeHandler;
import com.wayn.notify.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * websocket启用stomp消息配置
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Autowired
    private MyPrincipalHandshakeHandler myPrincipalHandshakeHandler;

    @Autowired
    private MessageBrokerConfig brokerConfig;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.setErrorHandler(new StompSubProtocolErrorHandler());
        stompEndpointRegistry.addEndpoint("/notify")
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
        if (Constant.BROKER_TYPE_ACTIVEMQ.equalsIgnoreCase(brokerConfig.getBrokerType())) {
            registry.setApplicationDestinationPrefixes("/app");//走@messageMapping
            registry.enableStompBrokerRelay("/topic", "/queue")
                    .setRelayHost(brokerConfig.getHost())
                    .setRelayPort(brokerConfig.getPort())
                    .setClientLogin(brokerConfig.getUsername())
                    .setClientPasscode(brokerConfig.getPassword())
                    .setSystemLogin(brokerConfig.getUsername())
                    .setSystemPasscode(brokerConfig.getPassword());
        } else {
            registry.enableSimpleBroker("/topic", "/queue");
        }
//        registry.setPathMatcher(new AntPathMatcher()); // 设置路径匹配规则
    }

    // 通过configureClientInboundChannel方法进行用户认证处理
    /*@Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        super.configureClientInboundChannel(registration);
        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    Principal user = new Principal() {
                        @Override
                        public String getName() {
                            return ShiroUtil.getSessionUser().getId();
                        }
                    }; // access authentication header(s)
                    accessor.setUser(user);
                }
                return message;
            }
        });
    }*/
}
