package com.wayn.framework.shiro.session;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.wayn.common.shiro.session.OnlineSession;
import com.wayn.common.util.JsonUtil;
import com.wayn.common.util.SerializeUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis session
 */
@Slf4j
@Component
public class RedisSessionDAO extends AbstractSessionDAO {

    @Resource
    private RedisTemplate<String, byte[]> binaryRedisTemplate;
    /**
     * shiro-redis的session对象前缀
     */
    private String keyPrefix = "crowd:shiro_redis_session:";

    /**
     * session获取时间
     */
    @Value("${shiro.sessionTimeout}")
    private Integer timeOut = 1800;

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
    @SneakyThrows
    private void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            log.error("session or session id is null");
            return;
        }
        binaryRedisTemplate.opsForValue().set(keyPrefix + session.getId(), SerializeUtils.serialize(session), timeOut, TimeUnit.SECONDS);
    }

    @SneakyThrows
    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            log.error("session id is null");
            return null;
        }
        return (Session) SerializeUtils.deserialize(binaryRedisTemplate.opsForValue().getAndExpire(keyPrefix + sessionId, timeOut, TimeUnit.SECONDS));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            log.error("session or session id is null");
            return;
        }
        binaryRedisTemplate.delete(keyPrefix + session.getId());
    }

    @SneakyThrows
    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<>();
        Set<String> keys = binaryRedisTemplate.keys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                Session s = (Session) SerializeUtils.deserialize(binaryRedisTemplate.opsForValue().getAndExpire(key, timeOut, TimeUnit.SECONDS));
                if (s == null) {
                    continue;
                }
                sessions.add(s);
            }
        }

        return sessions;
    }
}
