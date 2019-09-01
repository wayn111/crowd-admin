package com.wayn.framework.manager.mbean;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

/**
 * 配置ehcache的jmx监控
 */
@Profile({"ehcache"})
@Component
public class EhcacheExporter {

    @Autowired
    private CacheManager cacheManager;

    @Bean
    public ManagementService export() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ManagementService managementService = new ManagementService(cacheManager, mBeanServer, false, false, false, true);
        managementService.init();
        return managementService;
    }

}
