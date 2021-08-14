package com.wayn.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.constant.Constants;
import com.wayn.common.domain.User;
import com.wayn.common.exception.BusinessException;
import com.wayn.common.util.HttpUtil;
import com.wayn.common.util.ServletUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected ServletContext application;

    /**
     * 是否为 post 请求
     */
    protected boolean isPost() {
        return HttpUtil.isPost(request);
    }

    /**
     * 是否为 get 请求
     */
    protected boolean isGet() {
        return HttpUtil.isGet(request);
    }

    /**
     * <p>
     * 获取分页对象
     * </p>
     */
    protected <T> Page<T> getPage() {
        //设置通用分页
        try {
            Integer pageNumber = ServletUtil.getParameterToInt(Constants.PAGE_NUMBER);
            Integer pageSize = ServletUtil.getParameterToInt(Constants.PAGE_SIZE);
            String sortName = ServletUtil.getParameter(Constants.SORT_NAME);
            String sortOrder = ServletUtil.getParameter(Constants.SORT_ORDER);
            Page<T> tPage = new Page<>(pageNumber, pageSize);
            if (StringUtils.isNotEmpty(sortName)) {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(sortName);
                if (Constants.ORDER_DESC.equals(sortOrder)) {
                    orderItem.setAsc(false);
                }
                tPage.addOrder(orderItem);
            }
            return tPage;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return getPage(1, 10);
        }
    }


    /**
     * <p>
     * 获取分页对象
     * </p>
     */
    protected <T> Page<T> getPage(int pageNumber) {
        return getPage(pageNumber, 15);
    }

    /**
     * <p>
     * 获取分页对象
     * </p>
     *
     * @param pageNumber
     * @param pageSize
     * @param <T>
     * @return
     */
    protected <T> Page<T> getPage(int pageNumber, int pageSize) {
        return new Page<T>(pageNumber, pageSize);
    }

    /**
     * 重定向至地址 url
     *
     * @param url 请求地址
     * @return
     */
    protected String redirectTo(String url) {
        StringBuffer rto = new StringBuffer("redirect:");
        rto.append(url);
        return rto.toString();
    }

    /**
     * 返回 JSON 格式对象
     *
     * @param object 转换对象
     * @return
     */
    protected String toJson(Object object) {
        return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
    }

    /**
     * 返回 JSON 格式对象
     *
     * @param object 转换对象
     * @param format 序列化特点
     * @return
     */
    protected String toJson(Object object, String format) {
        if (format == null) {
            return toJson(object);
        }
        return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * 返回当前系统用户
     *
     * @return
     * @throws BusinessException
     */
    protected User getCurUser() throws BusinessException {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            throw new BusinessException("系统用户不存在");
        }
        Object principal = subject.getPrincipal();
        if (principal != null) {
            return (User) principal;
        }
        throw new BusinessException("系统用户不存在");
    }

    /**
     * 返回当前系统用户Id
     *
     * @return
     * @throws BusinessException
     */
    protected String getCurUserId() throws BusinessException {
        User curUser = getCurUser();
        if (curUser != null) {
            return curUser.getId();
        }
        return null;
    }

    /**
     * 返回当前系统会会话Id
     *
     * @return
     * @throws BusinessException
     */
    protected String getSessionId() throws BusinessException {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            throw new BusinessException("系统用户不存在");
        }
        Session session = subject.getSession();
        return session.getId().toString();
    }
}
