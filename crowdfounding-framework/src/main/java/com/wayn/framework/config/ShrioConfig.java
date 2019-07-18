package com.wayn.framework.config;

import com.wayn.commom.consts.Constant;
import com.wayn.framework.redis.RedisOpts;
import com.wayn.framework.shiro.cache.RedisCacheManager;
import com.wayn.framework.shiro.realm.MyRealm;
import com.wayn.framework.shiro.session.RedisSessionDAO;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

@Configuration
public class ShrioConfig {
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
	@Value("${session-timeout}")
	private int tomcatTimeout;

	@Autowired
	private RedisOpts opts;

	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/home/login");
		shiroFilterFactoryBean.setSuccessUrl("/");
		shiroFilterFactoryBean.setUnauthorizedUrl("/error/unauth");
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/home/*", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/main", "authc");
		filterChainDefinitionMap.put("/system/**", "authc");
		filterChainDefinitionMap.put("/monitor/**", "authc");
		filterChainDefinitionMap.put("/commom/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
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
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1024);
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
	 * 配置shiro redisManager
	 *
	 * @return
	 */

	/**
	 * cacheManager 缓存 redis实现
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	public RedisCacheManager rediscacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setOpts(opts);
		return redisCacheManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		return redisSessionDAO;
	}

	@Bean
	public SessionDAO sessionDAO() {
		if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
			return redisSessionDAO();
		} else {
			return new MemorySessionDAO();
		}
	}

	/**
	 * shiro session的管理
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
		sessionManager.setSessionDAO(sessionDAO());
		Collection<SessionListener> listeners = new ArrayList<SessionListener>();
		listeners.add(new BDSessionListener());
		sessionManager.setSessionListeners(listeners);
		return sessionManager;
	}

	public EhCacheManager shiroEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:cache/shiro-ehcache.xml");
		return em;
	}

}
