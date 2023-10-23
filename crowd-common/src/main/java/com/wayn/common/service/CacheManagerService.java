package com.wayn.common.service;

import org.springframework.cache.Cache;

import java.util.Set;

public interface CacheManagerService<T> {

    String[] getCacheNames();

    /**
     * 根据名称获取缓存对象
     *
     * @param name
     * @return
     */
    Cache getCache(String name);


    /**
     * 根据缓存名和key获取Element对象
     *
     * @param name
     * @param key
     * @return
     */
    T getElements(String name, String key);

    void putElements(String name, String key, T value);

    void removeElements(String name, String key);

    Set<T> getCacheAll(String name);

    Long ttl(String name, String key);
}
