package com.wayn.framework.shiro.session;

import com.wayn.commom.shiro.session.OnlineSession;
import com.wayn.commom.util.IpUtils;
import com.wayn.commom.util.UserAgentUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义自己的session示例
 */
public class OnlineSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext initData) {
        WebSessionContext sessionContext = (WebSessionContext) initData;
        HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
        OnlineSession session = new OnlineSession();
        if (request != null) {
            String userAgent = UserAgentUtils.getUserAgent(request);
            // 获取客户端操作系统
            String os = UserAgentUtils.getOs(request);
            // 获取客户端浏览器
            String browser = UserAgentUtils.getBrowser(userAgent).getName();
            session.setHost(IpUtils.getIpAddr(request));
            session.setBrowser(browser);
            session.setOs(os);
        }
        return session;
    }
}
