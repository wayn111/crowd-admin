package com.wayn.framework.web.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 配置cors授权
 */
public class MyCorsFilter implements Filter {

    private static final List<String> allowedOrigins = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedOrigins.addAll(Arrays.stream(filterConfig.getInitParameter("allowedOrigins").split(",", -1)).collect(Collectors.toList()));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String origin = req.getHeader("Origin");
        if (StringUtils.isBlank(origin)) {
            origin = req.getScheme() + "://" + req.getHeader("Host");
        }
        resp.addHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
        resp.addHeader("Access-Control-Allow-Methods", "*");
        resp.addHeader("Access-Control-Allow-Headers", "*");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
