package com.wayn.service.impl;

import com.wayn.domain.User;
import com.wayn.domain.UserOnline;
import com.wayn.service.UserOnlineService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserOnlineServiceImpl implements UserOnlineService {

	@Autowired
	private SessionDAO sessionDAO;

	@Override
	public List<UserOnline> list() {
		//获取当前系统在线用户
		Collection<Session> activeSessions = sessionDAO.getActiveSessions();
		List<UserOnline> list = activeSessions.stream().map(session -> {
			UserOnline userOnline = new UserOnline();
			if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
				return null;
			} else {
				SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
						.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
				User user = (User) primaryPrincipal;
				userOnline.setUsername(user.getUserName());
				userOnline.setOnlineSession(user.toString());
			}
			
			userOnline.setId((String) session.getId());
			userOnline.setHost(session.getHost());
			userOnline.setStartTimestamp(session.getStartTimestamp());
			userOnline.setLastAccessTime(session.getLastAccessTime());
			userOnline.setTimeout(session.getTimeout());
			return userOnline;
		}).filter(userOnline -> {
			return userOnline != null ? true : false;
		}).collect(Collectors.toList());
		return list;
	}

	@Override
	public void forceLogout(String sessionId) {
		Session session = sessionDAO.readSession(sessionId);
 		if(session != null) {
			session.stop();
			sessionDAO.delete(session);
		}
	}

}
