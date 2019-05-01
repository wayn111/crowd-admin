package com.wayn.service;

import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public interface CacheManagerService {

	public String[] getCacheNames();

	/**
	 * 根据名称获取缓存对象
	 * @param name
	 * @return
	 */
	public Ehcache getEhcache(String name);

	/**
	 * 获取缓存名中所有的key
	 * @param name
	 * @return
	 */
	public List getEhcacheKeys(String name);

	/**
	 * 根据缓存名和key获取Element对象
	 * @param name
	 * @param key
	 * @return
	 */
	public Element getElements(String name, String key);

	public List<Ehcache> getEhCaches();
}
