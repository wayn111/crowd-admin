package com.wayn.commom.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheUtil {

	private static CacheManager cacheManager;
	private static Cache cache;

	static {
		if (cacheManager == null) {
			cacheManager = CacheManager.create(
					"E:\\GitRepo\\github\\crowdfounding-parent\\crowdfounding-web\\src\\main\\resources\\cache\\shiro-cache.xml");
		}
		cache = cacheManager.getCache("myCache");
	}

	public static Object get(String key) {
		Element value = cache.get(key);

		return value == null ? null : value.getObjectValue();
	}
}
