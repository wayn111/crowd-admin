package com.wayn.framework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Profile({"dev", "docker"})
@Component
public class RedisOpts {

    // 0 - never expire
    private int expire = 0;

    public int getExpire() {
        return expire;
    }

    public RedisOpts setExpire(int expire) {
        this.expire = expire;
        return this;
    }

    @Autowired
    private JedisPool jedisPool;

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    public String get(String key) {
        String value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    public String set(String key, String value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * del
     *
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * flush
     */
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return dbSize;
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return keys;
    }

    public Set<String> keysStr(String pattern) {
        Set<String> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return keys;
    }
}

