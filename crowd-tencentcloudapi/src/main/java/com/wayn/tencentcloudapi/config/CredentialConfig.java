package com.wayn.tencentcloudapi.config;

import com.tencentcloudapi.cdn.v20180606.CdnClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:tencentcloud.properties")
public class CredentialConfig {

    @Value("${tencentcloud.host}")
    private String host;

    @Value("${tencentcloud.secretId}")
    private String secretId;

    @Value("${tencentcloud.secretKey}")
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
