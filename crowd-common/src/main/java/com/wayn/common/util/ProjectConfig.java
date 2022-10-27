package com.wayn.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wayn")
public class ProjectConfig {

    private String cacheType;
    private String uploadDir;
    private String adminId;
    private String logHandlerTempNum;

    public boolean isAdmin(String userId) {
        String adminId = this.getUploadDir();
        return adminId.equals(userId);
    }
}
