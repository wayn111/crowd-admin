package com.wayn.framework.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyCorsFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","*");
        resp.addHeader("Access-Control-Max-Age","3600");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.addHeader("Access-Control-Allow-Credentials","true");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
