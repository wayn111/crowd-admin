package com.wayn.tencentcloudapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:tencentcloud.properties")
public class CredentialConfig {

    private static String host;

    private static String secretId;

    private static String secretKey;

    public static String getSecretId() {
        return secretId;
    }

    @Value("${tencentcloud.secretId}")
    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    @Value("${tencentcloud.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public static String getHost() {
        return host;
    }

    @Value("${tencentcloud.host}")
    public static void setHost(String host) {
        CredentialConfig.host = host;
    }
}
