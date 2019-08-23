package com.wayn.framework.web.interceptor;

import com.google.gson.Gson;
import com.wayn.commom.util.Response;
import com.wayn.framework.annotation.RepeatSubmit;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 防重复提交拦截器
 */
@Component
public abstract class PreventRepeatSubmitInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request)) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    PrintWriter writer = null;
                    try {
                        writer = response.getWriter();
                        writer.print(new Gson().toJson(Response.error("不允许重复提交，请稍后再试")));
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        writer.close();
                    }
                    return false;
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request) throws Exception;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
