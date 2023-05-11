package com.wayn.common.service.impl;

import com.wayn.common.domain.User;
import com.wayn.common.domain.UserOnline;
import com.wayn.common.enums.OnlineStatusEnum;
import com.wayn.common.service.UserOnlineService;
import com.wayn.common.shiro.session.OnlineSession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UserOnlineServiceImpl implements UserOnlineService {

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private Ip2Region ip2Region;

    private Map<String, Object> map = new ConcurrentHashMap<>();

    @Override
    public void put(String key, Object value) {
        map.put(key, value);
    }

    @Override
    public void remove(String key) {
        map.remove(key);
    }

    @Override
    public Map<String, Object> getActiveMap() {
        return map;
    }

    @Override
    public List<UserOnline> list() {
        // 获取当前系统在线用户
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        return activeSessions.stream().map(session -> {
            UserOnline userOnline = new UserOnline();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                return null;
            } /*else {
                SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
                User user = (User) primaryPrincipal;
                userOnline.setUsername(user.getUserName());
                userOnline.setOnlineSession(user.toString());
            }*/
            if (session instanceof OnlineSession onlineSession) {
                userOnline.setUserId(onlineSession.getUserId());
                userOnline.setUsername(onlineSession.getUsername());
                userOnline.setDeptName(onlineSession.getDeptName());
                userOnline.setOs(onlineSession.getOs());
                userOnline.setBrowser(onlineSession.getBrowser());
                userOnline.setStatus(onlineSession.getStatus());
            }
            // 设置session属性至onlineUser中
            userOnline.setId((String) session.getId());
            userOnline.setOnlineSession(session.toString());
            userOnline.setHost(ip2Region.getCity(session.getHost()));
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setTimeout(session.getTimeout());
            return userOnline;

        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<User> listUser() {
        // 获取当前系统 SimplePrincipalCollection 中保存的在线用户
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        return activeSessions.stream().map(session -> {
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                return null;
            } else {
                SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
                return (User) primaryPrincipal;
            }

        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void forceLogout(String sessionId) {
        OnlineSession session = (OnlineSession) sessionDAO.readSession(sessionId);
        if (session != null) {
            session.setExpired(true);
            session.setTimeout(0);
            session.setStatus(OnlineStatusEnum.OFF_LINE.getType());
            sessionDAO.update(session);
        }
    }

    @Override
    public String getUserName(String sessionId) {
        OnlineSession session = (OnlineSession) sessionDAO.readSession(sessionId);
        if (session != null) {
            return session.getUsername();
        }
        return "未知用户";
    }

    @Override
    public boolean checkUserLogin(String userId) {
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        for (Session activeSession : activeSessions) {
            OnlineSession session = (OnlineSession) activeSession;
            if (session.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
