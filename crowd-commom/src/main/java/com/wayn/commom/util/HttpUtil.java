package com.wayn.commom.util;

import com.wayn.commom.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 允许 JS 跨域设置
     *
     * <p>
     * <!-- 使用 nginx 注意在 nginx.conf 中配置 -->
     * <p>
     * http {
     * ......
     * add_header Access-Control-Allow-Origin *;
     * ......
     * }
     * </p>
     *
     * <p>
     * 非 ngnix 下，如果该方法设置不管用、可以尝试增加下行代码。
     * <p>
     * response.setHeader("Access-Control-Allow-Origin", "*");
     * </p>
     *
     * @param response 响应请求
     */
    public static void allowJsCrossDomain(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    /**
     * <p>
     * 判断请求是否为 AJAX
     * </p>
     *
     * @param request 当前请求
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ? true : false;
    }

    /**
     * <p>
     * AJAX 设置 response 返回状态
     * </p>
     *
     * @param response
     * @param status   HTTP 状态码
     * @param tip
     */
    public static void ajaxStatus(HttpServletResponse response, int status, String tip) {
        try {
            response.setContentType("text/html;charset=" + Constants.UTF_ENCODING);
            response.setStatus(status);
            PrintWriter out = response.getWriter();
            out.print(tip);
            out.flush();
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * <p>
     * 获取当前 URL 包含查询条件
     * </p>
     *
     * @param request
     * @param encode  URLEncoder编码格式
     * @return
     * @throws IOException
     */
    public static String getQueryString(HttpServletRequest request, String encode) throws IOException {
        StringBuffer sb = new StringBuffer(request.getRequestURL());
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            sb.append("?").append(query);
        }
        return URLEncoder.encode(sb.toString(), encode);
    }

    /**
     * <p>
     * getRequestURL是否包含在URL之内
     * </p>
     *
     * @param request
     * @param url     参数为以';'分割的URL字符串
     * @return
     */
    public static boolean inContainURL(HttpServletRequest request, String url) {
        boolean result = false;
        if (url != null && !"".equals(url.trim())) {
            String[] urlArr = url.split(";");
            StringBuffer reqUrl = new StringBuffer(request.getRequestURL());
            for (int i = 0; i < urlArr.length; i++) {
                if (reqUrl.indexOf(urlArr[i]) > 1) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * <p>
     * URLEncoder 返回地址
     * </p>
     *
     * @param url      跳转地址
     * @param retParam 返回地址参数名
     * @param retUrl   返回地址
     * @return
     */
    public static String encodeRetURL(String url, String retParam, String retUrl) {
        return encodeRetURL(url, retParam, retUrl, null);
    }

    /**
     * <p>
     * URLEncoder 返回地址
     * </p>
     *
     * @param url      跳转地址
     * @param retParam 返回地址参数名
     * @param retUrl   返回地址
     * @param data     携带参数
     * @return
     */
    public static String encodeRetURL(String url, String retParam, String retUrl, Map<String, String> data) {
        if (url == null) {
            return null;
        }

        StringBuffer retStr = new StringBuffer(url);
        retStr.append("?");
        retStr.append(retParam);
        retStr.append("=");
        try {
            retStr.append(URLEncoder.encode(retUrl, Constants.UTF_ENCODING));
        } catch (UnsupportedEncodingException e) {
            logger.error("encodeRetURL error." + url);
            e.printStackTrace();
        }

        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                retStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }

        return retStr.toString();
    }

    /**
     * <p>
     * URLDecoder 解码地址
     * </p>
     *
     * @param url 解码地址
     * @return
     */
    public static String decodeURL(String url) {
        if (url == null) {
            return null;
        }
        String retUrl = "";

        try {
            retUrl = URLDecoder.decode(url, Constants.UTF_ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error("encodeRetURL error." + url);
            e.printStackTrace();
        }

        return retUrl;
    }

    /**
     * <p>
     * GET 请求
     * </p>
     *
     * @param request
     * @return boolean
     */
    public static boolean isGet(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * POST 请求
     * </p>
     *
     * @param request
     * @return boolean
     */
    public static boolean isPost(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 请求重定向至地址 location
     * </p>
     *
     * @param response 请求响应
     * @param location 重定向至地址
     */
    public static void sendRedirect(HttpServletResponse response, String location) {
        try {
            response.sendRedirect(location);
        } catch (IOException e) {
            logger.error("sendRedirect location:" + location);
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * 获取Request Playload 内容
     * </p>
     *
     * @param request
     * @return Request Playload 内容
     */
    public static String requestPlayload(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(Constants.UTF_ENCODING)));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * <p>
     * 获取当前完整请求地址
     * </p>
     *
     * @param request
     * @return 请求地址
     */
    public static String getRequestUrl(HttpServletRequest request) {
        StringBuffer url = new StringBuffer(request.getScheme());
        // 请求协议 http,https
        url.append("://");
        url.append(request.getHeader("host"));// 请求服务器
        url.append(request.getRequestURI());// 工程名
        if (request.getQueryString() != null) {
            // 请求参数
            url.append("?").append(request.getQueryString());
        }
        return url.toString();
    }

    /**
     * 获取当前项目路径，<br>
     * 例如：http://localhost:8081/crowd-admin
     *
     * @param request
     * @return
     */
    public static String getRequestContext(HttpServletRequest request) {
        StringBuffer url = new StringBuffer(request.getScheme());
        // 请求协议 http,https
        url.append("://");
        url.append(request.getHeader("host"));// 请求服务器
        url.append(request.getContextPath());// 工程名
        return url.toString();
    }

    /**
     * 获取请求头信息
     *
     * @param request 请求对象
     * @return 请求头信息
     */
    public static String getHeaders(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name).append(" = ").append(request.getHeader(name)).append("\n");
        }
        // logger.info("request header: {}", sb);
        return sb.toString();
    }

    /**
     * 过滤HTTP Response Splitting攻击
     *
     * @param value 响应头
     * @return string
     */
    public static String safeHttpHeader(String value) {
        String temp;
        temp = value.replaceAll("\n", "");
        temp = temp.replaceAll("\r", "");
        return temp;
    }

    public static String getAttachementFileName(String fileName, String userAgent) throws UnsupportedEncodingException {
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();

            if (userAgent.contains("msie")) {
                return "filename=\"" + URLEncoder.encode(fileName, "UTF8") + "\"";
            }

            if (userAgent.contains("opera")) {
                return "filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF8");
            }
            if (userAgent.contains("safari")) {
                return "filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1") + "\"";
            }
            if (userAgent.contains("mozilla")) {
                return "filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF8");
            }
        }

        return "filename=\"" + URLEncoder.encode(fileName, "UTF8") + "\"";
    }
}
