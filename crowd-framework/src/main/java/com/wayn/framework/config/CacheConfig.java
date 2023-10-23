package com.wayn.framework.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.wayn.common.constant.Constants;
import com.wayn.common.util.ProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.time.Duration;
import java.util.Objects;

@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Autowired
    private ProjectConfig projectConfig;

    @Bean
    public RedisTemplate<String, byte[]> binaryRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        if (redisConnectionFactory instanceof LettuceConnectionFactory lettuceConnectionFactory) {
            lettuceConnectionFactory.setValidateConnection(true);
        }
        RedisTemplate<String, byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        if (redisConnectionFactory instanceof LettuceConnectionFactory lettuceConnectionFactory) {
            lettuceConnectionFactory.setValidateConnection(true);
        }
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericFastJsonRedisSerializer();
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, net.sf.ehcache.CacheManager ehCacheManager) {
        if (Constants.CACHE_TYPE_REDIS.equals(projectConfig.getCacheType())) {
            // 配置序列化（解决乱码的问题）
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                    //设置key为String
                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                    //设置value为自动转Json的Object
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                    .computePrefixWith(name -> Constants.REDIS_KEY_PREFIX + Constants.REDIS_KEY_SEPARATOR + name + Constants.REDIS_KEY_SEPARATOR)
                    .entryTtl(Duration.ofMinutes(5))
                    //不缓存null
                    .disableCachingNullValues();
            RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(Objects.requireNonNull(redisConnectionFactory));
            return new RedisCacheManager(redisCacheWriter, config);
        } else {
            return new EhCacheCacheManager(ehCacheManager);
        }
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        return net.sf.ehcache.CacheManager
                .newInstance(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("cache/ehcache.xml")));
    }

    @Bean(name = "cacheKeyGenerator")
    public KeyGenerator cacheKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
