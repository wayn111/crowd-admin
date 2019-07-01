package com.wayn.framework.aspect;

import com.alibaba.fastjson.JSONObject;
import com.wayn.domain.User;
import com.wayn.framework.annotation.Log;
import com.wayn.framework.manager.LogQueue;
import com.wayn.framework.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogQueue logQueue;

    @Pointcut("@annotation(com.wayn.framework.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 当方法正常返回时执行
     *
     * @param joinPoint
     */
    @AfterReturning("logPointCut()")
    public void doAfter(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Log log = method.getAnnotation(Log.class);
        User user = ShiroUtil.getSessionUser();
        if (log != null) {
            com.wayn.domain.Log log2 = new com.wayn.domain.Log();
            log2.setCreateTime(new Date());
            log2.setModuleName(log.value());
            log2.setOperation(log.operator().getName());
            log2.setUserName((user != null) ? user.getUserName() : "游客");
            log2.setUrl(request.getRequestURI().toString());
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (log.isNeedParam()) {
                JSONObject obj = new JSONObject(true);
                parameterMap.forEach((key, value) -> {
                    if (value.length == 1 && StringUtils.isNotEmpty(value[0])) {
                        obj.put(key, value[0]);
                    } else {
                        obj.put(key, value);
                    }
                });
                log2.setRequestParams(obj.toJSONString());
            }
            logQueue.add(log2);
        }
    }
}
