package com.wayn.common.service.impl;

import com.wayn.common.service.CacheManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CacheManagerServiceImpl implements CacheManagerService {

    @Autowired
    private CacheManager cacheManager;


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
    public Object getElements(String name, String key) {
        Cache.ValueWrapper valueWrapper = cacheManager.getCache(name).get(key);
        Object o = valueWrapper.get();
        return o;
    }

    @Override
    public <T> T getElements(String name, String key, Class<T> type) {
        T t = cacheManager.getCache(name).get(key, type);
        return t;
    }

    @Override
    public void putElements(String name, String key, Object value) {
        cacheManager.getCache(name).put(key,value);
    }

}
