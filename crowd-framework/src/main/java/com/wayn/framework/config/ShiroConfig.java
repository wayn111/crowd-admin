package com.wayn.framework.config;

import com.wayn.common.constant.Constants;
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
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${wayn.cacheType}")
    private String cacheType;
    @Value("${shiro.sessionTimeout}")
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

    /**
     * 设置Cookie的域名
     */
    @Value("${shiro.cookie.domain}")
    private String domain;

    /**
     * 设置cookie的有效访问路径
     */
    @Value("${shiro.cookie.path}")
    private String path;

    /**
     * 设置HttpOnly属性
     */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /**
     * 设置Cookie的过期时间，秒为单位
     */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("onlineSession", onlineSessionFilter());
        shiroFilterFactoryBean.setFilters(filters);
        // 定义拦过滤器链
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/ws/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugin/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/home/*", "anon");
        filterChainDefinitionMap.put("/**", "user,onlineSession");
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
        // 设置realm.
        securityManager.setRealm(userRealm());
        // 记住我
        securityManager.setRememberMeManager(rememberMeManager());
        // 自定义缓存实现 使用redis
        if (Constants.CACHE_TYPE_REDIS.equals(cacheType)) {
            securityManager.setCacheManager(rediscacheManager());
        } else if (Constants.CACHE_TYPE_EACHACEH.equals(cacheType)) {
            securityManager.setCacheManager(shiroEhCacheManager());
        }
        // session管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * cookie 属性设置
     */
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        return cookie;
    }

    /**
     * 记住我
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    @Bean
    public MyRealm userRealm() {
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
        return new RedisCacheManager(redisTemplate, "crowd:shrio-cahche:");
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
        if (Constants.CACHE_TYPE_REDIS.equals(cacheType)) {
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
        sessionIdCookie.setName("wayn.session");
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        // 设置sessionValidation任务执行周期时间
        sessionManager.setSessionValidationInterval(30 * 60 * 1000);
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
