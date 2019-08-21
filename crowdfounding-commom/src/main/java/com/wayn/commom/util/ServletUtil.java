package com.wayn.commom.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ServletUtil {

    private static ServletUtil getInstance;

    static {
        if (getInstance == null) {
            getInstance = new ServletUtil();
        }
    }

    /**
     * 获取requestAttributes
     *
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取当前请求request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取当前请求response
     *
     * @return HttpServletRequest
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Integer.parseInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取所有参数
     */
    public static Map<String, String[]> getAllParameter() {
        Map<String, String[]> parameterMap = getRequest().getParameterMap();
        return parameterMap;
    }

    /**
     * 设置参数
     *
     * @param name
     * @param value
     * @return
     */
    public static ServletUtil setParameter(String name, Object value) {
        getRequest().setAttribute(name, value);
        return getInstance;
    }
}
