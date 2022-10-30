package com.wayn.framework.web.filter;

import com.wayn.common.domain.Dept;
import com.wayn.common.domain.User;
import com.wayn.common.enums.OnlineStatusEnum;
import com.wayn.common.service.DeptService;
import com.wayn.common.shiro.session.OnlineSession;
import com.wayn.common.shiro.util.ShiroUtil;
import com.wayn.common.util.SpringContextUtil;
import lombok.Data;
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
import java.util.Objects;


/**
 * 定义自己的shiro过滤器
 */
@Data
public class OnlineSessionFilter extends AccessControlFilter {

    private static final String ONLINE_SESSION = "online_session";


    /**
     * 强制退出后重定向的地址
     */
    private String forceLogoutUrl;

    private SessionDAO sessionDAO;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null) {
            return true;
        }
        Session session = sessionDAO.readSession(subject.getSession().getId());
        if (session instanceof OnlineSession onlineSession) {
            request.setAttribute(ONLINE_SESSION, onlineSession);
            // 把user id设置进去
            boolean isGuest = StringUtils.isBlank(onlineSession.getUserId());
            if (isGuest) {
                User user = ShiroUtil.getSessionUser();
                if (user != null) {
                    onlineSession.setUserId(user.getId());
                    onlineSession.setUsername(user.getUserName());
                    Dept dept = SpringContextUtil.getBean(DeptService.class).getById(user.getDeptId());
                    onlineSession.setDeptName(dept.getDeptName());
                    sessionDAO.update(onlineSession);
                }
                return !Objects.equals(onlineSession.getStatus(), OnlineStatusEnum.OFF_LINE.getType());
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
