package com.wayn.quartz.util;

import com.wayn.commom.util.DateUtils;
import com.wayn.commom.util.SpringContextUtil;
import com.wayn.quartz.domain.Job;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 任务执行工具
 *
 * @author ruoyi
 */
public class JobInvokeUtil {

    /**
     * 执行方法
     *
     * @param sysJob 系统任务
     */
    public static void invokeMethod(Job sysJob) throws Exception {
        String invokeTarget = sysJob.getInvokeTarget();
        String beanName = getBeanName(invokeTarget);
        String methodName = getMethodName(invokeTarget);
        List<Object[]> methodParams = getMethodParams(invokeTarget);

        if (!isValidPackageName(beanName)) {
            Object bean = SpringContextUtil.getBean(beanName);
            invokeMethod(bean, methodName, methodParams);
        } else {
            Object bean = Class.forName(beanName).newInstance();
            invokeMethod(bean, methodName, methodParams);
        }
    }

    /**
     * 调用任务方法
     *
     * @param bean         目标对象
     * @param methodName   方法名称
     * @param methodParams 方法参数
     */
    private static void invokeMethod(Object bean, String methodName, List<Object[]> methodParams)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        if (Objects.nonNull(methodParams) && methodParams.size() > 0) {
            Method method = bean.getClass().getDeclaredMethod(methodName, getMethodParamsType(methodParams));
            method.invoke(bean, getMethodParamsValue(methodParams));
        } else {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }

    /**
     * 校验是否为为class包名
     *
     * @param invokeTarget 名称
     * @return true是 false否
     */
    public static boolean isValidPackageName(String invokeTarget) {
        return StringUtils.countMatches(invokeTarget, ".") > 1;
    }

    /**
     * 获取bean名称
     *
     * @param invokeTarget 目标字符串
     * @return bean名称
     */
    public static String getBeanName(String invokeTarget) {
        String beanName = StringUtils.substringBefore(invokeTarget, "(");
        return StringUtils.substringBeforeLast(beanName, ".");
    }

    /**
     * 获取bean方法
     *
     * @param invokeTarget 目标字符串
     * @return method方法
     */
    public static String getMethodName(String invokeTarget) {
        String methodName = StringUtils.substringBefore(invokeTarget, "(");
        return StringUtils.substringAfterLast(methodName, ".");
    }

    /**
     * 获取method方法参数相关列表
     *
     * @param invokeTarget 目标字符串
     * @return method方法相关参数列表
     */
    public static List<Object[]> getMethodParams(String invokeTarget) {
        String methodStr = StringUtils.substringBetween(invokeTarget, "(", ")");
        if (StringUtils.isEmpty(methodStr)) {
            return null;
        }
        String[] methodParams = methodStr.split(",");
        List<Object[]> classs = new LinkedList<>();
        for (String methodParam : methodParams) {
            String str = StringUtils.trimToEmpty(methodParam);
            // String字符串类型，包含'
            if (StringUtils.contains(str, "'")) {
                // date类型，str可被解析为date对象
                String replace = StringUtils.replace(str, "'", "");
                classs.add(DateUtils.checkDateStrIsValid(str) ? new Object[]{DateUtils.parseDate(replace), Date.class}
                        : new Object[]{replace, String.class});
            }
            // boolean布尔类型，等于true或者false
            else if (StringUtils.equals(str, "true") || StringUtils.equalsIgnoreCase(str, "false")) {
                classs.add(new Object[]{Boolean.valueOf(str), Boolean.class});
            }
            // long长整形，包含L
            else if (StringUtils.containsIgnoreCase(str, "L")) {
                classs.add(new Object[]{Long.valueOf(StringUtils.replaceIgnoreCase(str, "L", "")), Long.class});
            }
            // double浮点类型，包含D
            else if (StringUtils.containsIgnoreCase(str, "D")) {
                classs.add(new Object[]{Double.valueOf(StringUtils.replaceIgnoreCase(str, "D", "")), Double.class});
            }
            // 其他类型归类为整形
            else {
                classs.add(new Object[]{Integer.valueOf(str), Integer.class});
            }
        }
        return classs;
    }

    /**
     * 获取参数类型
     *
     * @param methodParams 参数相关列表
     * @return 参数类型列表
     */
    public static Class<?>[] getMethodParamsType(List<Object[]> methodParams) {
        Class<?>[] classs = new Class<?>[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = (Class<?>) os[1];
            index++;
        }
        return classs;
    }

    /**
     * 获取参数值
     *
     * @param methodParams 参数相关列表
     * @return 参数值列表
     */
    public static Object[] getMethodParamsValue(List<Object[]> methodParams) {
        Object[] classs = new Object[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = os[0];
            index++;
        }
        return classs;
    }

    public static boolean checkInvokeTargetIsValid(String invokeTarget) throws Exception {
        String method = StringUtils.substringBefore(invokeTarget, "(");
        String methodName = StringUtils.substringAfterLast(method, ".");
        String className = StringUtils.substringBeforeLast(method, ".");
        // 是否包含包名
        if (className.contains(".")) {
            Class<?> aClass = Class.forName(className);
            List<Object[]> methodParams = JobInvokeUtil.getMethodParams(invokeTarget);
            if (Objects.nonNull(methodParams) && methodParams.size() > 0) {
                aClass.getDeclaredMethod(methodName, JobInvokeUtil.getMethodParamsType(methodParams));
            } else {
                aClass.getDeclaredMethod(methodName);
            }
            return true;
        } else {
            Object bean = SpringContextUtil.getBean(className);
            Class<?> aClass = bean.getClass();
            List<Object[]> methodParams = JobInvokeUtil.getMethodParams(invokeTarget);
            if (Objects.nonNull(methodParams) && methodParams.size() > 0) {
                aClass.getDeclaredMethod(methodName, JobInvokeUtil.getMethodParamsType(methodParams));
            } else {
                aClass.getDeclaredMethod(methodName);
            }
            return true;
        }
    }
}
