package com.wayn.framework.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.wayn.commom.util.SpringUtil;
import com.wayn.domain.User;
import com.wayn.framework.annotation.Log;
import com.wayn.framework.util.ShiroUtil;
import com.wayn.service.LogService;

@Aspect
@Component
public class LogAspect {

	@Pointcut("@annotation(com.wayn.framework.annotation.Log)")
	public void logPointCut() {
	}

	/**
	 * 当方法正常返回是执行
	 * @param joinPoint
	 */
	@AfterReturning("logPointCut()")
	public void doBefore(JoinPoint joinPoint) {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Log log = method.getAnnotation(Log.class);
		User user = ShiroUtil.getSessionUser();
		if (log != null) {
			com.wayn.domain.Log log2 = new com.wayn.domain.Log();
			log2.setCreateTime(new Date());
			log2.setTitle(log.value());
			log2.setUserName((user != null) ? user.getUserName() : "system");
			log2.setUrl(request.getRequestURI().toString());
			log2.setParams(new Gson().toJson(request.getParameterMap()));
			SpringUtil.getBean(LogService.class).insert(log2);
		}
	}
}
