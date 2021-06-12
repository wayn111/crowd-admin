package com.wayn.notify.manager.mbean;

import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.util.HashMap;
import java.util.Map;

@Component
public class MbeanManager {

    /**
     * 导出WebSocketMessageBrokerStats信息·
     * @param webSocketMessageBrokerStats
     * @return  mBeanExporter
     */
    @Bean
    public MBeanExporter mBeanExporter(WebSocketMessageBrokerStats webSocketMessageBrokerStats) {
        MBeanExporter mBeanExporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("webSocket:name=webSocketMessageBrokerStats", webSocketMessageBrokerStats);
        mBeanExporter.setBeans(beans);
        return mBeanExporter;
    }

}
