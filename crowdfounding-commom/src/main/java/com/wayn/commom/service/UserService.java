package com.wayn.commom.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.User;
import com.wayn.commom.domain.vo.Tree;
import com.wayn.commom.domain.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface UserService extends IService<User> {

    Page<UserVO> listPage(Page<User> page, User user);

    boolean exit(Map<String, Object> params);

    boolean save(User user, String roleIds);

    boolean update(User user, String roleIds);

    boolean remove(String id);

    boolean batchRemove(String[] ids);

    boolean resetPwd(String id, String password);

    boolean editAccount(String id, String userName);

    Tree<Dept> getTree();

    /**
     * 将所有用户组装成select2需要的json对象
     *
     * @return
     */
    List<JSONObject> selectUser2JsonObj();

    void export(User user, HttpServletResponse response, HttpServletRequest request) throws IOException;
}
