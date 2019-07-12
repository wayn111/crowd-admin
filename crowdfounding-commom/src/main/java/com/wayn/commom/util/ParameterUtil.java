package com.wayn.commom.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang3.StringUtils;

/**
 * 公用查询参数帮助类，例如时间查询。。。
 */
public class ParameterUtil {
    private static ThreadLocal<EntityWrapper> threadLocal = new ThreadLocal<>();

    public static <T> void set() {
        EntityWrapper<T> wrapper = new EntityWrapper<T>();
        String startTime = ServletUtil.getParameter("startTime");
        String endTime = ServletUtil.getParameter("endTime");
        wrapper.ge(StringUtils.isNotEmpty(startTime), "createTime", startTime + " 00:00:00");
        wrapper.le(StringUtils.isNotEmpty(endTime), "createTime", endTime + " 23:59:59");
        threadLocal.set(wrapper);
    }

    public static <T> EntityWrapper<T> get() {
        return threadLocal.get();
    }


}
