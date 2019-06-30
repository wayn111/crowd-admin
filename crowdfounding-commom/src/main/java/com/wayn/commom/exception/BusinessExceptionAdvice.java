package com.wayn.commom.exception;

import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.HttpUtil;
import com.wayn.commom.util.Response;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BusinessExceptionAdvice extends BaseControlller {

	/**
	 * 处理认证异常
	 * @param e
	 * @param request
	 * @return
	 */
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", e.getMessage());
		return new ModelAndView("error/500", map);
	}
}
