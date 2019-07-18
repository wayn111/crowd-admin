package com.wayn.commom.exception;

import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.HttpUtil;
import com.wayn.commom.util.Response;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionAdvice extends BaseControlller {

    /**
     * 处理登陆认证异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public Object handleAuthorizationException(AuthenticationException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        return Response.error(e.getMessage());
    }

    /**
     * 处理为授权异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    public Object handleShiroException(ShiroException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpUtil.isAjax(request)) {
            return Response.error(e.getMessage());
        }
        return new ModelAndView("error/unauth");
    }

    /**
     * 处理404异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handleShiroException(NoHandlerFoundException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpUtil.isAjax(request)) {
            return Response.error("您请求路径不存在，请检查url！");
        }
        return new ModelAndView("error/404");
    }

    /**
     * 处理自定义异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({BusinessException.class})
    public Object handleBusinessException(BusinessException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpUtil.isAjax(request)) {
            return Response.error(e.getMessage());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", e.getMessage());
        return new ModelAndView("error/500", map);
    }

    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpUtil.isAjax(request)) {
            return Response.error("服务器内部错误！");
        }
        return new ModelAndView("error/500");
    }
}
