package com.wayn.framework.config;

import com.wayn.commom.consts.Constant;
import com.wayn.framework.redis.RedisOpts;
import com.wayn.framework.shiro.cache.RedisCacheManager;
import com.wayn.framework.shiro.credentials.MyCredentialsMatcher;
import com.wayn.framework.shiro.realm.MyRealm;
import com.wayn.framework.shiro.session.EhCacheSessionDAO;
import com.wayn.framework.shiro.session.OnlineSessionFactory;
import com.wayn.framework.shiro.session.RedisSessionDAO;
import com.wayn.framework.web.filter.OnlineSessionFilter;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${cache.type}")
    private String cacheType;
    @Value("${shiro.session-timeout}")
    private int sessionTimeout;
    @Value("${shiro.retryCount}")
    private int retryCount;
    @Value("${shiro.algorithmName}")
    private String algorithmName;
    @Value("${shiro.iterations}")
    private int iterations;
    @Value("${shiro.loginUrl}")
    private String loginUrl;
    @Value("${shiro.successUrl}")
    private String successUrl;
    @Value("${shiro.unauthorizedUrl}")
    private String unauthorizedUrl;


    @Autowired(required = false)
    private RedisOpts opts;

    @Autowired
    private CacheManager ehCacheManager;

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        shiroFilterFactoryBean.setSuccessUrl(successUrl);
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        // 定义自己的过滤器
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("onlineSession", onlineSessionFilter());
        shiroFilterFactoryBean.setFilters(filters);
        // 定义拦过滤器链
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/home/*", "anon");
        filterChainDefinitionMap.put("/**", "authc,onlineSession");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public Filter onlineSessionFilter() {
        OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
        onlineSessionFilter.setForceLogoutUrl(loginUrl);
        onlineSessionFilter.setSessionDAO(sessionDAO());
        return onlineSessionFilter;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(userRealm());
        // 自定义缓存实现 使用redis
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            securityManager.setCacheManager(rediscacheManager());
        } else {
            securityManager.setCacheManager(shiroEhCacheManager());
        }
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    MyRealm userRealm() {
        MyRealm userRealm = new MyRealm();
        // 定义自己的的密码验证服务
        MyCredentialsMatcher credentialsMatcher = new MyCredentialsMatcher();
        credentialsMatcher.setPasswordRetryCache(ehCacheManager.getCache("passwordRetryCache"));
        credentialsMatcher.setRetryCount(retryCount);
        credentialsMatcher.setHashAlgorithmName(algorithmName);
        credentialsMatcher.setHashIterations(iterations);
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    /**
     * 启动shiro注解
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     */
    private RedisCacheManager rediscacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setOpts(opts);
        return redisCacheManager;
    }

    /**
     * cacheManager 缓存 ehcahe实现
     * 使用的是shiro-redis开源插件
     */
    private EhCacheManager shiroEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:cache/shiro-ehcache.xml");
        return em;
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    private RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setTimeOut(sessionTimeout);
        return redisSessionDAO;
    }

    /**
     * EhCacheSessionDAO shiro sessionDao层的实现 通过ehcache
     * 使用的是shiro-redis开源插件
     */
    private EhCacheSessionDAO ehCacheSessionDAO() {
        EhCacheSessionDAO ehCacheSessionDAO = new EhCacheSessionDAO();
        ehCacheSessionDAO.setOnlineUser(ehCacheManager.getCache("onlineUser"));
        return ehCacheSessionDAO;
    }

    @Bean
    public SessionDAO sessionDAO() {
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            return redisSessionDAO();
        } else {
            return ehCacheSessionDAO();
        }
    }

    public SessionFactory sessionFactory() {
        return new OnlineSessionFactory();
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置cookie
        SimpleCookie sessionIdCookie = new SimpleCookie();
        sessionIdCookie.setName("wayn-session-id");
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // 设置全局session超时时间
        sessionManager.setGlobalSessionTimeout(sessionTimeout * 1000);
        // 设置sessionDao实现
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionFactory(sessionFactory());
        // 设置session监听
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new BDSessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }

}
