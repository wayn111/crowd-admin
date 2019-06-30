package com.wayn.service.impl;

import com.wayn.service.CacheManagerService;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CacheManagerServiceImpl implements CacheManagerService {

    @Autowired()
    public EhCacheCacheManager ehCacheManager;

    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        cacheManager = ehCacheManager.getCacheManager();

    }

    /**
     * 获取所有缓存的名称
     *
     * @return
     */
    public String[] getCacheNames() {
        return cacheManager.getCacheNames();
    }

    /**
     * 根据名称获取缓存对象
     *
     * @param name
     * @return
     */
    public Ehcache getEhcache(String name) {
        return cacheManager.getEhcache(name);
    }

    /**
     * 获取缓存名中所有的key
     *
     * @param name
     * @return
     */
    public List getEhcacheKeys(String name) {
        return getEhcache(name).getKeys();
    }

    /**
     * 根据缓存名和key获取Element对象
     *
     * @param name
     * @param key
     * @return
     */
    public Element getElements(String name, String key) {
        Ehcache cache = getEhcache(name);
        if (cache == null) {
            return null;
        }

        return cache.getQuiet(key);
    }

    public List<Ehcache> getEhCaches() {
        String[] cacheNames = getCacheNames();
        List<Ehcache> list = new ArrayList<Ehcache>();
        for (String cacheName : cacheNames) {
            list.add(cacheManager.getEhcache(cacheName));
        }
        return list;
    }
}
