package com.wayn.framework.shiro.session;

import com.wayn.common.shiro.session.OnlineSession;
import com.wayn.common.util.SerializeUtils;
import io.lettuce.core.KeyScanArgs;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.KeyScanOptions;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis session
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class RedisSessionDAO extends AbstractSessionDAO {

    private RedisTemplate<String, byte[]> binaryRedisTemplate;
    /**
     * shiro-redis的session对象前缀
     */
    private String keyPrefix;

    /**
     * session获取时间
     */
    private Integer timeOut;

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
        if (session == null) {
            log.error("session or session id is null");
            return;
        }
        Serializable id = session.getId();
        binaryRedisTemplate.opsForValue().set(keyPrefix + id, SerializeUtils.serialize(session), timeOut, TimeUnit.SECONDS);
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
        Set<String> keys = new HashSet<>();
        try (Cursor<String> cursor = binaryRedisTemplate.scan(KeyScanOptions.scanOptions()
                .match(this.keyPrefix + "*")
                .count(1000)
                .build())) {
            while (cursor.hasNext()) {
                String key = cursor.next();
                keys.add(key);
            }
        }
        if (!keys.isEmpty()) {
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
