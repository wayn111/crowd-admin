package com.wayn.common.service.impl;

import com.wayn.common.constant.Constants;
import com.wayn.common.service.CacheManagerService;
import com.wayn.common.util.ProjectConfig;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.KeyScanOptions;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wayn.common.constant.Constants.*;

@Service
public class CacheManagerServiceImpl<T> implements CacheManagerService<T> {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private ProjectConfig projectConfig;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String[] getCacheNames() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        return cacheNames.toArray(new String[]{});
    }

    @Override
    public Cache getCache(String name) {
        return cacheManager.getCache(name);
    }

    @Override
    public T getElements(String name, String key) {
        Cache.ValueWrapper valueWrapper = cacheManager.getCache(name).get(key);
        return (T) valueWrapper.get();
    }

    @Override
    public void putElements(String name, String key, Object value) {
        cacheManager.getCache(name).put(key, value);
    }

    @Override
    public void removeElements(String name, String key) {
        cacheManager.getCache(name).evict(key);
    }

    @Override
    public Set<T> getCacheAll(String name) {
        if (Constants.CACHE_TYPE_REDIS.equals(projectConfig.getCacheType())) {
            Set<T> sessions = new HashSet<>();
            Set<String> keys = new HashSet<>();
            try (Cursor<String> cursor = redisTemplate.scan(KeyScanOptions.scanOptions()
                    .match(REDIS_KEY_PREFIX + REDIS_KEY_SEPARATOR + name + REDIS_KEY_SEPARATOR + "*")
                    .count(1000)
                    .build())) {
                while (cursor.hasNext()) {
                    String key = cursor.next();
                    keys.add(key);
                }
            }
            if (!keys.isEmpty()) {
                for (String key : keys) {
                    Object o = redisTemplate.opsForValue().get(key);
                    if (o == null) {
                        continue;
                    }
                    sessions.add((T) o);
                }
            }
            return sessions;
        } else {
            net.sf.ehcache.Cache cache = (net.sf.ehcache.Cache) cacheManager.getCache(name).getNativeCache();
            Results results = cache.createQuery().includeValues().execute();
            List<Result> all = results.all();
            List<T> collect = all.stream()
                    .map(result -> (T) result.getValue())
                    .collect(Collectors.toList());
            return new HashSet<>(collect);
        }
    }


    @Override
    public Long ttl(String name, String key) {
        if (Constants.CACHE_TYPE_REDIS.equals(projectConfig.getCacheType())) {
            return System.currentTimeMillis() + redisTemplate.getExpire(REDIS_KEY_PREFIX + REDIS_KEY_SEPARATOR + name + REDIS_KEY_SEPARATOR + key) * 1000;
        } else {
            net.sf.ehcache.Cache cache = (net.sf.ehcache.Cache) cacheManager.getCache(name).getNativeCache();
            Element element = cache.get(key);
            return element.getExpirationTime();
        }
    }
}
