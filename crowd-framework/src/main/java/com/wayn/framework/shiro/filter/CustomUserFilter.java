package com.wayn.framework.shiro.filter;

import com.wayn.common.util.HttpUtil;
import com.wayn.common.util.JsonUtil;
import com.wayn.common.util.Response;
import com.wayn.common.util.ServletUtil;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomUserFilter extends UserFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (HttpUtil.isAjax(httpServletRequest)) {
            Response error = Response.error("请先登录！");
            ServletUtil.renderString(httpServletResponse, JsonUtil.marshal(error));
            return false;
        }
        return super.onAccessDenied(request, response);
    }
}
