package com.wayn.framework.shiro.session;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ehcache的Shiro sessionDAO实现
 */


public class EhCacheSessionDAO extends AbstractSessionDAO {


    private Cache onlineUser;

    public Cache getOnlineUser() {
        return onlineUser;
    }

    public EhCacheSessionDAO setOnlineUser(Cache onlineUser) {
        this.onlineUser = onlineUser;
        return this;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    private Session saveSession(Session session) {
        if (session.getId() == null) {
            throw new NullPointerException("session id properties cannot be null.");
        }
        Element element = new Element(session.getId(), session);
        onlineUser.putIfAbsent(element);
        return session;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (Objects.isNull(onlineUser.get(sessionId))) {
            return null;
        }
        return (Session) onlineUser.get(sessionId).getObjectValue();
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        Serializable id = session.getId();
        if (id != null) {
            onlineUser.remove(id);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Results results = onlineUser.createQuery().includeValues().execute();
        List<Result> all = results.all();
        List<Session> collect = all.stream().map(result -> (Session) result.getValue()).collect(Collectors.toList());
        return collect;
    }
}
