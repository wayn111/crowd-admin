package com.wayn.framework.aspect;

import com.alibaba.fastjson.JSONObject;
import com.wayn.commom.consts.Constant;
import com.wayn.commom.domain.User;
import com.wayn.commom.util.UserAgentUtils;
import com.wayn.framework.annotation.Log;
import com.wayn.framework.manager.LogQueue;
import com.wayn.framework.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
    @AfterReturning(value = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handerLog(joinPoint, null);
    }

    /**
     * 当方法正常返回时执行
     *
     * @param joinPoint
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handerLog(joinPoint, e);
    }

    /**
     * 日志处理方法
     *
     * @param joinPoint
     * @param e
     */
    public void handerLog(JoinPoint joinPoint, Exception e) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        //获取日志注解
        Log log = method.getAnnotation(Log.class);
        User user = ShiroUtil.getSessionUser();
        if (log != null) {
            //创建操作日志对象
            com.wayn.commom.domain.Log log2 = new com.wayn.commom.domain.Log();
            log2.setCreateTime(new Date());
            log2.setModuleName(log.value());
            log2.setOperation(log.operator().getName());
            log2.setUserName((user != null) ? user.getUserName() : "游客");
            log2.setUrl(StringUtils.substring(request.getRequestURI(), 0, 100));
            log2.setIp(ShiroUtil.getIP());
            log2.setOperState(Constant.OPERATOR_SUCCESS);
            log2.setAgent(UserAgentUtils.getUserAgent(request));
            //保存操作参数
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
            if (e != null) {
                log2.setOperState(Constant.OPERATOR_fail);
                log2.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            logQueue.add(log2);
        }
    }
}
