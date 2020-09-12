package com.wayn.commom.constant;

public class Constant {
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
     * 系统环境变量，默认为dev
     */
    public static String ENV = "dev";

    /**
     * 操作状态，成功
     */
    public static final Integer OPERATOR_SUCCESS = 1;
    /**
     * 操作状态，失败
     */
    public static final Integer OPERATOR_fail = -1;

    /**
     * 缓存方式
     */
    public static String CACHE_TYPE_REDIS = "redis";
    public static String CACHE_TYPE_EACHACEH = "ehcache";

    /**
     * 当前页
     */
    public static String PAGE_NUMBER = "pageNumber";

    /**
     * 分页大小
     */
    public static String PAGE_SIZE = "pageSize";

    /**
     * 排序字段名
     */
    public static String SORT_NAME = "sortName";

    /**
     * 排序方式 asc或者desc
     */
    public static String SORT_ORDER = "sortOrder";
}
