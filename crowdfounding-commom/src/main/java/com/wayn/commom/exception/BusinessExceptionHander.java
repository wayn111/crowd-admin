package com.wayn.commom.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.HttpUtil;
import com.wayn.commom.util.Response;

@RestControllerAdvice
public class BusinessExceptionHander extends BaseControlller {

	@ExceptionHandler(AuthenticationException.class)
	public Object handleAuthorizationException(AuthenticationException e, HttpServletRequest request) {
		logger.error(e.getMessage(), e);
		return Response.error(e.getMessage());
	}

	@ExceptionHandler({ BusinessException.class })
	public Object handleBusinessException(BusinessException e, HttpServletRequest request) {
		logger.error(e.getMessage(), e);
		if (HttpUtil.isAjax(request)) {
			return Response.error(e.getMessage());
		}
		return new ModelAndView("error/403");
	}

	@ExceptionHandler({ Exception.class })
	public Object handleException(Exception e, HttpServletRequest request) {
		logger.error(e.getMessage(), e);
		if (HttpUtil.isAjax(request)) {
			return Response.error("服务器内部错误");
		}
		return new ModelAndView("error/500");
	}
}
