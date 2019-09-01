package com.wayn.framework.web.filter;

import com.wayn.commom.domain.User;
import com.wayn.commom.enums.OnlineStatus;
import com.wayn.commom.shiro.session.OnlineSession;
import com.wayn.framework.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class OnlineSessionFilter extends AccessControlFilter {

    private static final String ONLINE_SESSION = "online_session";

    /**
     * 强制退出后重定向的地址
     */
    private String forceLogoutUrl;

    private SessionDAO sessionDAO;

    public String getForceLogoutUrl() {
        return forceLogoutUrl;
    }

    public OnlineSessionFilter setForceLogoutUrl(String forceLogoutUrl) {
        this.forceLogoutUrl = forceLogoutUrl;
        return this;
    }

    public SessionDAO getSessionDAO() {
        return sessionDAO;
    }

    public OnlineSessionFilter setSessionDAO(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
        return this;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null) {
            return true;
        }
        Session session = sessionDAO.readSession(subject.getSession().getId());
        if (session != null && session instanceof OnlineSession) {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ONLINE_SESSION, onlineSession);
            //把user id设置进去
            boolean isGuest = StringUtils.isEmpty(onlineSession.getUserId());
            if (isGuest == true) {
                User user = ShiroUtil.getSessionUser();
                if (user != null) {
                    onlineSession.setUserId(user.getId());
                    onlineSession.setUsername(user.getUserName());
                    onlineSession.markAttributeChanged();
                }
            }
            if (onlineSession.getStatus() == OnlineStatus.off_line) {
                return false;
            }
        }
        return true;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject != null) {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(request, response);
        return true;
    }


    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, getForceLogoutUrl());
    }

}
