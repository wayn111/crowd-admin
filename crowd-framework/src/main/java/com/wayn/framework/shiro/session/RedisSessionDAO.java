package com.wayn.framework.shiro.session;

import com.wayn.commom.util.SerializeUtils;
import com.wayn.framework.redis.RedisOpts;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * redis的Shiro sessionDAO实现
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
    /**
     * shiro-redis的session对象前缀
     */
    @Autowired
    private RedisOpts redisOpts;

    /**
     * session获取时间
     */
    private Integer timeOut;

    /**
     * The Redis key prefix for the sessions
     */
    private String keyPrefix = "shiro_redis_session:";


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
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

        byte[] key = getByteKey(session.getId());
        byte[] value = SerializeUtils.serialize(session);
        //设置session失效时间
        this.redisOpts.set(key, value, timeOut);
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
        redisOpts.del(this.getByteKey(session.getId()));

    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();

        Set<byte[]> keys = redisOpts.keys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (byte[] key : keys) {
                Session s = (Session) SerializeUtils.deserialize(redisOpts.get(key));
                sessions.add(s);
            }
        }

        return sessions;
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        }
        return (Session) SerializeUtils.deserialize(redisOpts.get(this.getByteKey(sessionId)));
    }

    /**
     * 获得byte[]型的key
     *
     * @param sessionId
     * @return
     */
    private byte[] getByteKey(Serializable sessionId) {
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public RedisSessionDAO setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
        return this;
    }
}