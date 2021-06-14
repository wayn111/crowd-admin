package com.wayn.framework.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 检测当前系统profile配置
 */
@WebListener
public class ProfileListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //侦测jvm环境，并缓存到全局变量中
        String env = System.getProperty("spring.profiles.active");

        System.out.println("==================================================================================================");
        System.out.println("The Application " + sce.getServletContext().getInitParameter("applicationName") + " is running on the environment:" + env);
        System.out.println("==================================================================================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
}
