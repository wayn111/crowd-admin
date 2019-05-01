package com.wayn.service;

import com.wayn.domain.User;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface UserService extends IService<User> {

	Page<User> listPage(Page<User> page, Map<String, Object> params);

	boolean exit(Map<String, Object> params);

	boolean save(User user, String roleIds);

	boolean update(User user, String roleIds);

	boolean remove(String id);

	boolean batchRemove(String[] ids);

	boolean resetPwd(String id, String password);

}
