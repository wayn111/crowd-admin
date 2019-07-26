package com.wayn.framework.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wayn.commom.consts.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Value("${cache.type}")
    private String cacheType;

    @Value("${redis.host}")
    private String host = "127.0.0.1";

    @Value("${redis.port}")
    private int port = 6379;

    @Value("${redis.password}")
    private String password = "";

    //timeout for jedis try to connect to redis server, not expire time! In milliseconds
    @Value("${redis.timeout}")
    private int timeout = 0;

    // 0 - never expire
    @Value("${redis.expire}")
    private int expire = 0;

    @Profile({"dev", "docker"})
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        connectionFactory.setHostName(host);
        connectionFactory.setPort(port);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Profile({"dev", "docker"})
    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //return jackson2JsonRedisSerializer;

        // 设置序列化 两种方式区别不大
        return new JdkSerializationRedisSerializer();
    }

    @Primary
    @Bean
    public CacheManager cacheManager(@Autowired(required = false) RedisTemplate<String, Object> redisTemplate) {
        CacheManager cacheManager = null;
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            cacheManager = new RedisCacheManager(redisTemplate);
            ((RedisCacheManager) cacheManager).setUsePrefix(true);
            ((RedisCacheManager) cacheManager).setDefaultExpiration(expire);
            //定义缓存名称
            List<String> cacheNames = new ArrayList<String>();
            cacheNames.add("menuCache");
            cacheNames.add("deptCache");
            cacheNames.add("permissionCache");
            cacheNames.add("dictCache");
            ((RedisCacheManager) cacheManager).setCacheNames(cacheNames);
        } else {
            cacheManager = ehCacheCacheManager();
        }
        return cacheManager;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        net.sf.ehcache.CacheManager cacheManager1 = net.sf.ehcache.CacheManager
                .newInstance(getClass().getClassLoader().getResource("cache/ehcache.xml"));
        ehCacheCacheManager.setCacheManager(cacheManager1);
        return ehCacheCacheManager;
    }

    @Bean(name = "cacheKeyGenerator")
    public KeyGenerator cacheKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
