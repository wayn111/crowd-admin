package com.wayn.framework.shiro.cache;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RedisCacheManager2 implements CacheManager {

    /**
     * 用于shiro中用到的cache
     */
    private ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

    /**
     * redis cache 工具类
     */
    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public RedisCacheManager2 setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        return this;
    }

    //    @Autowired
//    private RedisTemplate redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache<K, V> cache = caches.get(name);
        if (cache == null) {
            synchronized (this) {
                cache = new RedisCache3<>(3600, redisTemplate);
                caches.put(name, cache);
            }
        }
        return cache;
    }

}
