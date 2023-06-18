package com.wayn.tencentcloudapi.config;

import com.tencentcloudapi.cdn.v20180606.CdnClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:tencentcloud.properties")
@ConfigurationProperties(prefix = "tencentcloud")
public class CredentialConfig {

    private String host;

    private String secretId;

    private String secretKey;

    @Bean
    public CdnClient getCdnClient() {
        Credential cred = new Credential(secretId, secretKey);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(host);

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        return new CdnClient(cred, "", clientProfile);
    }
}
