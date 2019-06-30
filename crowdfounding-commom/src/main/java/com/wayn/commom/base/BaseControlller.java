package com.wayn.commom.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.consts.Constant;
import com.wayn.commom.exception.BusinessException;
import com.wayn.commom.util.HttpUtil;
import com.wayn.commom.util.ServletUtil;
import com.wayn.domain.User;
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
import java.util.Map;

public class BaseControlller {
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
	protected <T> Page<T> getPage(int pageNumber) {
		return getPage(pageNumber, 15);
	}

	/**
	 * <p>
	 * 获取分页对象
	 * </p>
	 * @param pageNumber
	 * @param pageSize
	 * @param <T>
	 * @return
	 */
	protected <T> Page<T> getPage(int pageNumber, int pageSize) {
		return new Page<T>(pageNumber, pageSize);
	}

	/**
	 * <p>
	 * 获取分页对象
	 * </p>
	 *
	 * @param params 分页查询参数
	 * @return
	 */
	protected <T> Page<T> getPage(Map<String, Object> params) {
		int pageNumber = Integer.parseInt(params.remove("pageNumber").toString());
		int pageSize = Integer.parseInt(params.remove("pageSize").toString());
		String sortName = params.remove("sortName").toString();
		String sortOrder = params.remove("sortOrder").toString();
		if ("desc".equals(sortOrder)) {
			return new Page<T>(pageNumber, pageSize, sortName, false);
		} else {
			return new Page<T>(pageNumber, pageSize, sortName, true);
		}

	}

	protected <T> Page<T> getPage() {
		Integer pageNumber = ServletUtil.getParameterToInt(Constant.PAGE_NUMBER);
		Integer pageSize = ServletUtil.getParameterToInt(Constant.PAGE_SIZE);
		String sortName = ServletUtil.getParameter(Constant.SORT_NAME);
		String sortOrder = ServletUtil.getParameter(Constant.SORT_ORDER);
		if ("desc".equals(sortOrder)) {
			return new Page<T>(pageNumber, pageSize, sortName, false);
		} else {
			return new Page<T>(pageNumber, pageSize, sortName, true);
		}
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
	 * @param object   转换对象
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
