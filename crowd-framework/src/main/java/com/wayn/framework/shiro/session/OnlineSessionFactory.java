package com.wayn.framework.shiro.session;

import com.wayn.common.shiro.session.OnlineSession;
import com.wayn.common.util.IpUtils;
import com.wayn.common.util.UserAgentUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义自己的session示例
 */
public class OnlineSessionFactory implements SessionFactory {

    private static final Logger logger = LoggerFactory.getLogger(OnlineSessionFactory.class);


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
            String ipAddr = IpUtils.getIpAddr(request);
            logger.info("createSession ip: {}", ipAddr);
            session.setHost(ipAddr);
            session.setBrowser(browser);
            session.setOs(os);
        }
        return session;
    }
}
