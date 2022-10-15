package com.wayn.framework.web.interceptor;

import com.wayn.common.annotation.RepeatSubmit;
import com.wayn.common.util.JsonUtil;
import com.wayn.common.util.Response;
import com.wayn.common.util.ServletUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 防重复提交拦截器
 */
@Component
public abstract class PreventRepeatSubmitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request)) {
                    Response error = Response.error("不允许重复提交，请稍后再试");
                    ServletUtil.renderString(response, JsonUtil.marshal(error));
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request 请求对象
     * @return boolean
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request) throws Exception;
}
