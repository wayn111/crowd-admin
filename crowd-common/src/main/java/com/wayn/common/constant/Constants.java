package com.wayn.common.constant;

public class Constants {

    /**
     * 验证码key
     */
    public static final String CAPTCHA_SESSION_KEY = "captcha";
    /**
     * 字符编码
     */
    public static final String UTF_ENCODING = "UTF-8";
    /**
     * 字符串表示true
     */
    public static final String TRUE = "true";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "1";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "-1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 操作状态，成功
     */
    public static final Integer OPERATOR_SUCCESS = 1;
    /**
     * 操作状态，失败
     */
    public static final Integer OPERATOR_fail = -1;
    /**
     * 排序方式 asc或者desc
     */
    public static final String SORT_ORDER = "sortOrder";
    public static final String ORDER_DESC = "desc";
    /**
     * 缓存方式
     */
    public static final String CACHE_TYPE_REDIS = "redis";
    public static final String CACHE_TYPE_EACHACEH = "ehcache";
    public static final String REDIS_KEY_SEPARATOR = ":";
    public static final String REDIS_KEY_PREFIX = "crowd";
    /**
     * 当前页
     */
    public static final String PAGE_NUMBER = "pageNumber";
    /**
     * 分页大小
     */
    public static final String PAGE_SIZE = "pageSize";
    /**
     * 排序字段名
     */
    public static final String SORT_NAME = "sortName";
}
