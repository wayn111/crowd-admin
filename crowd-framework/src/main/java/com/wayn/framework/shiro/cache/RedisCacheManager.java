package com.wayn.framework.shiro.cache;

/**
 * 定义实现shiro.cache.CacheManager的RedisCacheManager
 */

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RedisCacheManager implements CacheManager {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    private final ConcurrentMap<String, RedisCache<String, Object>> caches = new ConcurrentHashMap<>();

    private RedisTemplate<String, Object> redisTemplate;

    private String keyPrefix;

    public RedisCacheManager(RedisTemplate<String, Object> redisTemplate, String keyPrefix) {
        this.redisTemplate = redisTemplate;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");

        RedisCache<String, Object> redisCache = caches.get(name);

        if (redisCache == null) {

            // create a new cache instance
            redisCache = new RedisCache<>(redisTemplate, keyPrefix);

            // add it to the cache collection
            caches.put(name, redisCache);
        }
        return (Cache<K, V>) redisCache;
    }

}
