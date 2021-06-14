package com.wayn.framework.web.filter;

import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.User;
import com.wayn.commom.enums.OnlineStatus;
import com.wayn.commom.service.DeptService;
import com.wayn.commom.shiro.session.OnlineSession;
import com.wayn.commom.shiro.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


/**
 * 定义自己的shiro过滤器
 */
public class OnlineSessionFilter extends AccessControlFilter {

    private static final String ONLINE_SESSION = "online_session";

    @Autowired
    private DeptService deptService;


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
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null) {
            return true;
        }
        Session session = sessionDAO.readSession(subject.getSession().getId());
        if (session instanceof OnlineSession) {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ONLINE_SESSION, onlineSession);
            //把user id设置进去
            boolean isGuest = StringUtils.isBlank(onlineSession.getUserId());
            if (isGuest) {
                User user = ShiroUtil.getSessionUser();
                if (user != null) {
                    onlineSession.setUserId(user.getId());
                    onlineSession.setUsername(user.getUserName());
                    Dept dept = deptService.getById(user.getDeptId());
                    onlineSession.setDeptName(dept.getDeptName());
                    sessionDAO.update(onlineSession);
                }
                return onlineSession.getStatus() != OnlineStatus.OFF_LINE;
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
