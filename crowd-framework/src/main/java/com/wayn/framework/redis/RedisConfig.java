package com.wayn.framework.redis;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 */
@Profile({"dev"})
@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String host = "127.0.0.1";

    @Value("${redis.port}")
    private int port = 6379;

    //timeout for jedis try to connect to redis server, not expire time! In milliseconds
    @Value("${redis.timeout}")
    private int timeout = 0;

    @Value("${redis.password}")
    private String password = "";

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.maxTotal}")
    private int maxTotal;

    @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis;


    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
        JedisPool jedisPool = null;
        if (StringUtils.isNotEmpty(password)) {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        }
        return jedisPool;
    }
}
