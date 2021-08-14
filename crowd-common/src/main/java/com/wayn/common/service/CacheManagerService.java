package com.wayn.common.service;

import org.springframework.cache.Cache;

public interface CacheManagerService {

    public String[] getCacheNames();

    /**
     * 根据名称获取缓存对象
     *
     * @param name
     * @return
     */
    public Cache getCache(String name);


    /**
     * 根据缓存名和key获取Element对象
     *
     * @param name
     * @param key
     * @return
     */
    public Object getElements(String name, String key);

    public <T> T getElements(String name, String key, Class<T> type);

    public void putElements(String name, String key, Object value);

}
