package com.wayn.framework.shiro.cache;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCache3<K, V> implements Cache<K, V> {
	private long expireTime;// 缓存的超时时间，单位为s

	/**
	 * 用于shiro的cache的名字
	 */
	private String cacheName = "shiro_redis_session";

	private RedisTemplate<K, V> redisTemplate; // 通过构造方法注入该对象

	public RedisCache3() {
		super();
	}

	public RedisCache3(long expireTime, RedisTemplate<K, V> redisTemplate) {
		super();
		this.expireTime = expireTime;
		this.redisTemplate = redisTemplate;
	}

	public K cacheKey(String cacheName, K key) {
		return (K) (keyPrefix(cacheName) + key);
	}

	public String keyPrefix(String cacheName) {
		return cacheName + ":";
	}

	/**
	 * 通过key来获取对应的缓存对象
	 */
	@Override
	public V get(K key) throws CacheException {
		return redisTemplate.opsForValue().get(cacheKey(cacheName, key));
	}

	/**
	 * 将权限信息加入缓存中
	 */
	@Override
	public V put(K key, V value) throws CacheException {
		if ("loginRecordCache".equals(cacheName)) {
			redisTemplate.opsForValue().set(cacheKey(cacheName, key), value, 600, TimeUnit.SECONDS);
		} else {
			redisTemplate.opsForValue().set(cacheKey(cacheName, key), value, expireTime, TimeUnit.SECONDS);
		}
		return value;
	}

	/**
	 * 将权限信息从缓存中删除
	 */
	@Override
	public V remove(K key) throws CacheException {
		V v = redisTemplate.opsForValue().get(cacheKey(cacheName, key));
		redisTemplate.opsForValue().getOperations().delete(cacheKey(cacheName, key));
		return v;
	}

	@Override
	public void clear() throws CacheException {

	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Set<K> keys() {
		return null;
	}

	@Override
	public Collection<V> values() {
		return null;
	}

}
