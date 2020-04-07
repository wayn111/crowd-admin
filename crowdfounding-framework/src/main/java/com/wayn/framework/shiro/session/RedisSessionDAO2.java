package com.wayn.framework.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
public class RedisSessionDAO2 extends AbstractSessionDAO {
    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO2.class);

    private RedisTemplate<String, Object> redisTemplate;
    /**
     * shiro-redis的session对象前缀
     */
    private String keyPrefix = "shiro_redis_session:";

    /**
     * session获取时间
     */
    private Integer timeOut;

	public Integer getTimeOut() {
		return timeOut;
	}

	public RedisSessionDAO2 setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
		return this;
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public RedisSessionDAO2 setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        return this;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public RedisSessionDAO2 setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
        return this;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    /**
     * save session
     *
     * @param session
     * @throws UnknownSessionException
     */
    private void saveSession(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        redisTemplate.opsForValue().set(String.valueOf(session.getId()),session,timeOut, TimeUnit.SECONDS);
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        }
		redisTemplate.expire(String.valueOf(sessionId), timeOut, TimeUnit.SECONDS);
        return (Session) redisTemplate.opsForValue().get(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        redisTemplate.delete(String.valueOf(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<>();
        Set<String> keys = redisTemplate.keys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                Session s = (Session) redisTemplate.opsForValue().get(key);
                sessions.add(s);
            }
        }

        return sessions;
    }
}