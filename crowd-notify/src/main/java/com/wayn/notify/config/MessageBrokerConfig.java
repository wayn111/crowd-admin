package com.wayn.notify.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 消息代理配置
 */
@Configuration
public class MessageBrokerConfig {

    @Value("${wayn.message.brokerType}")
    private String brokerType;
    @Value("${activemq.host}")
    private String host;
    @Value("${activemq.port}")
    private int port;
    @Value("${activemq.username}")
    private String username;
    @Value("${activemq.password}")
    private String password;

    public String getBrokerType() {
        return brokerType;
    }

    public MessageBrokerConfig setBrokerType(String brokerType) {
        this.brokerType = brokerType;
        return this;
    }

    public String getHost() {
        return host;
    }

    public MessageBrokerConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public MessageBrokerConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MessageBrokerConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MessageBrokerConfig setPassword(String password) {
        this.password = password;
        return this;
    }
}
