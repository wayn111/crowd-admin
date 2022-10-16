package com.wayn.framework.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class RedisCache<K, V> implements Cache<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    /**
     * The wrapped Jedis instance.
     */
    private RedisTemplate<K, V> redisTemplate;

    /**
     * The Redis key prefix for the sessions
     */
    private String keyPrefix;

    public RedisCache(RedisTemplate<K, V> redisTemplate, String keyPrefix) {
        super();
        this.redisTemplate = redisTemplate;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public V get(K key) throws CacheException {
        logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        try {
            if (key == null) {
                return null;
            } else {
                return redisTemplate.opsForValue().get(key);
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }

    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.debug("根据key从存储 key [" + key + "]");
        try {
            redisTemplate.opsForValue().set(key, value);
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.debug("从redis中删除 key [" + key + "]");
        try {
            V previous = get(key);
            redisTemplate.delete(key);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("从redis中删除所有元素");
        try {
            Set keys = this.scan(keyPrefix + "*");
            redisTemplate.delete(keys);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * 获取匹配的所有key，使用scan避免阻塞
     *
     * @param pattern 匹配keys的规则
     * @return 返回获取到的keys
     */
    public Set<K> scan(String pattern) {
        return redisTemplate.execute((RedisCallback<Set<K>>) connection -> {
            Set<K> keysTmp = new HashSet<>();
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions()
                    .match(pattern)
                    .count(10000).build())) {
                while (cursor.hasNext()) {
                    keysTmp.add((K) new String(cursor.next(), StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
            return keysTmp;
        });
    }

    @Override
    public int size() {
        try {
            return this.scan(keyPrefix + "*").size();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Set<K> keys() {
        try {
            Set<K> set = this.scan(keyPrefix + "*");
            if (CollectionUtils.isEmpty(set)) {
                return Collections.emptySet();
            } else {
                return set;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        try {
            Set<K> keys = this.scan(this.keyPrefix + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<>(keys.size());
                for (K key : keys) {
                    V value = get(key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

}
