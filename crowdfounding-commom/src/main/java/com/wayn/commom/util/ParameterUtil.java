package com.wayn.commom.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wayn.commom.base.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 公用查询参数帮助类，例如时间查询。。。
 */
public class ParameterUtil {
    private static final String BASE_ENTITY = "BaseEntity";
    private static final ThreadLocal<QueryWrapper> entityWrapperThreadLocal = new ThreadLocal<>();

    /**
     * 设置wrapper类得通用查询属性
     *
     * @param <T>
     */
    public static <T> void setWrapper() {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        String startTime = ServletUtil.getParameter("startTime");
        String endTime = ServletUtil.getParameter("endTime");
        ServletUtil.setParameter("startTime", startTime);
        ServletUtil.setParameter("endTime", endTime);
        wrapper.ge(StringUtils.isNotEmpty(startTime), "createTime", startTime + " 00:00:00");
        wrapper.le(StringUtils.isNotEmpty(endTime), "createTime", endTime + " 23:59:59");
        entityWrapperThreadLocal.set(wrapper);
    }

    /**
     * 设置baseEntity对象及其子类得通用查询属性
     *
     * @param baseEntity
     * @param <T>
     */
    public static <T> void set(BaseEntity baseEntity) {
        Map<String, String[]> allParameter = ServletUtil.getAllParameter();
        allParameter.forEach((String k, String[] v) -> {
            if (StringUtils.containsIgnoreCase(k, "startTime")) {
                String startTime = ServletUtil.getParameter(k);
                if (StringUtils.isNotEmpty(startTime)) {
                    startTime = startTime + " 00:00:00";
                    Class<? extends BaseEntity> aClass = baseEntity.getClass();
                    if (StringUtils.equalsIgnoreCase("startTime", k)) {
                        Class<?> superclass = aClass.getSuperclass();
                        while (!BASE_ENTITY.equals(superclass.getSimpleName())) {
                            superclass = superclass.getSuperclass();
                        }
                        try {
                            Method declaredMethod = superclass.getDeclaredMethod("set" + StringUtils.capitalize(k), String.class);
                            declaredMethod.setAccessible(true);
                            declaredMethod.invoke(baseEntity, startTime);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    Field[] declaredFields = aClass.getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        if (StringUtils.containsIgnoreCase(declaredField.getName(), "startTime")) {
                            try {
                                Method method = aClass.getDeclaredMethod("set" + StringUtils.capitalize(declaredField.getName()), String.class);
                                method.setAccessible(true);
                                method.invoke(baseEntity, startTime);
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } else if (StringUtils.containsIgnoreCase(k, "endTime")) {
                String endTime = ServletUtil.getParameter(k);
                if (StringUtils.isNotEmpty(endTime)) {
                    endTime = endTime + " 23:59:59";
                    Class<? extends BaseEntity> aClass = baseEntity.getClass();
                    if (StringUtils.equalsIgnoreCase("endTime", k)) {
                        Class<?> superclass = aClass.getSuperclass();
                        while (!BASE_ENTITY.equals(superclass.getSimpleName())) {
                            superclass = superclass.getSuperclass();
                        }
                        try {
                            Method declaredMethod = superclass.getDeclaredMethod("set" + StringUtils.capitalize(k), String.class);
                            declaredMethod.setAccessible(true);
                            declaredMethod.invoke(baseEntity, endTime);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    Field[] declaredFields = aClass.getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        if (StringUtils.containsIgnoreCase(declaredField.getName(), "endTime")) {
                            try {
                                Method method = aClass.getMethod("set" + StringUtils.capitalize(declaredField.getName()), String.class);
                                method.setAccessible(true);
                                method.invoke(baseEntity, endTime);
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    public static <T> QueryWrapper<T> get() {
        return entityWrapperThreadLocal.get();
    }


}
