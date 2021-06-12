package com.wayn.framework.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class XssFilter implements Filter {

    private static boolean enabled = true;

    private static List<String> excludes = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        enabled = Boolean.valueOf(filterConfig.getInitParameter("enabled"));
        excludes.addAll(Arrays.stream(filterConfig.getInitParameter("excludes").split("," , -1)).collect(Collectors.toList()));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (handerExcludeUrl(req)) {
            chain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    public boolean handerExcludeUrl(HttpServletRequest request) {
        if (!enabled) {
            return true;
        }
        String servletPath = request.getServletPath();
        String uri = servletPath;
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(uri);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
