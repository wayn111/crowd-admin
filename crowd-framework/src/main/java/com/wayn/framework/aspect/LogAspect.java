package com.wayn.framework.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wayn.common.annotation.Log;
import com.wayn.common.constant.Constants;
import com.wayn.common.domain.OperLog;
import com.wayn.common.domain.User;
import com.wayn.common.enums.Operator;
import com.wayn.common.exception.BusinessException;
import com.wayn.common.service.ConfigService;
import com.wayn.common.shiro.util.ShiroUtil;
import com.wayn.common.util.IP2RegionUtil;
import com.wayn.common.util.ServletUtil;
import com.wayn.common.util.UserAgentUtils;
import com.wayn.framework.manager.log.LogQueue;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class LogAspect {

    private static final ThreadLocal<Long> startTimeLocal = ThreadLocal.withInitial(() -> 0L);

    @Autowired
    private LogQueue logQueue;

    @Autowired
    private ConfigService configService;

    @Pointcut("@annotation(com.wayn.common.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 当方法执行前
     *
     * @param joinPoint 切点
     */
    @Before(value = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        startTimeLocal.set(System.nanoTime());
        viewModelHandler(joinPoint);
    }

    /**
     * 当方法正常返回时执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(value = "logPointCut()", returning = "response")
    public void doAfterReturning(JoinPoint joinPoint, Object response) {
        handlerLog(joinPoint, null, response);
    }

    /**
     * 当方法异常返回时执行
     *
     * @param joinPoint 切点
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handlerLog(joinPoint, e, null);
    }

    /**
     * 日志处理方法
     *
     * @param joinPoint 切点
     * @param e         异常
     * @param response  切面方法返回
     */
    private void handlerLog(JoinPoint joinPoint, Exception e, Object response) {
        long executeTime = System.nanoTime() - startTimeLocal.get();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        HttpServletRequest request = ServletUtil.getRequest();
        // 获取日志注解
        Log log = method.getAnnotation(Log.class);
        User user = ShiroUtil.getSessionUser();
        if (log != null) {
            // 创建操作日志对象
            OperLog operLog = new OperLog();
            operLog.setCreateTime(new Date());
            operLog.setModuleName(log.value());
            operLog.setOperation(log.operator().getCode());
            operLog.setUserName((user != null) ? user.getUserName() : "游客");
            operLog.setUrl(StringUtils.substring(request.getRequestURI(), 0, 100));
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            operLog.setMethod(className + "." + methodName + "()");
            operLog.setIp(IP2RegionUtil.getCityInfo(ShiroUtil.getIP()));
            operLog.setOperState(Constants.OPERATOR_SUCCESS);
            operLog.setAgent(UserAgentUtils.getUserAgent(request));
            operLog.setRequestMethod(request.getMethod());
            operLog.setExecuteTime(executeTime / 1000000);
            // 保存请求响应
            if (Objects.nonNull(response)) {
                operLog.setRequestResponse(StringUtils.substring(JSON.toJSONString(response), 0, 2000));
            }
            // 保存请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (log.isNeedParam()) {
                JSONObject obj = new JSONObject(true);
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    String key = entry.getKey();
                    String[] value = entry.getValue();
                    if (value.length == 1 && StringUtils.isNotEmpty(value[0])) {
                        obj.put(key, value[0]);
                    } else {
                        obj.put(key, value);
                    }
                }
                operLog.setRequestParams(StringUtils.substring(obj.toJSONString(), 0, 2000));
            }
            if (e != null) {
                operLog.setOperState(Constants.OPERATOR_fail);
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            logQueue.add(operLog);
            startTimeLocal.remove();
        }
    }

    /**
     * 判断是否是演示模式，是的话则不能进行更新或者删除
     */
    private void viewModelHandler(JoinPoint joinPoint) {
        boolean viewModel = Boolean.parseBoolean(configService.getValueByKey("sys.viewModel"));
        if (!viewModel) {
            return;
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 获取日志注解
        Log log = method.getAnnotation(Log.class);
        Operator operator = log.operator();
        String name = log.operator().getName();
        switch (operator) {
            case UPDATE:
                // fall through
            case DELETE:
                throw new BusinessException(String.format("演示模式，请勿%s", name));
        }
    }
}
