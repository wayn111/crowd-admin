package com.wayn.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.domain.User;
import com.wayn.domain.UserRole;
import com.wayn.mapper.UserDao;
import com.wayn.service.DeptService;
import com.wayn.service.UserRoleService;
import com.wayn.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private DeptService deptService;

    @Override
    public Page<User> listPage(Page<User> page, User user) {
        EntityWrapper<User> wrapper = ParameterUtil.get();
        wrapper.like("userName", user.getUserName());
        wrapper.eq(user.getUserState() != null, "userState", user.getUserState());
        wrapper.eq(user.getDeptId() != null, "deptId", user.getDeptId());
        Page<User> selectPage = selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        String userName = params.get("userName").toString();
        //如果是修改用户，用户名未改变则通过校验
        if (params.get("id") != null) {
            String id = params.get("id").toString();
            if (userDao.selectById(id).getUserName().equals(userName)) {
                return false;
            }
        }
        Integer count = userDao.selectCount(new EntityWrapper<User>().eq("userName", userName));
        return count > 0;
    }

    @Transactional
    @Override
    public boolean save(User user, String roleIds) {
        user.setCreateTime(new Date());
        user.setPassword(new SimpleHash("MD5", user.getPassword(), user.getUserName(), 1024).toString());
        boolean flag = insert(user);
        List<UserRole> list = new ArrayList<UserRole>();
        if (StringUtils.isNotBlank(roleIds)) {
            String[] split = roleIds.split(",");
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getId());
                list.add(userRole);
            }
        }
        userRoleService.delete(new EntityWrapper<UserRole>().eq("userId", user.getId()));
        if (list.size() > 0) {
            userRoleService.insertBatch(list);
        }
        return flag;
    }

    @Transactional
    @Override
    public boolean update(User user, String roleIds) {
        boolean flag = updateById(user);
        List<UserRole> list = new ArrayList<UserRole>();
        if (StringUtils.isNotBlank(roleIds)) {
            String[] split = roleIds.split(",");
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getId());
                list.add(userRole);
            }
        }
        userRoleService.delete(new EntityWrapper<UserRole>().eq("userId", user.getId()));
        if (list.size() > 0) {
            userRoleService.insertBatch(list);
        }
        return flag;
    }

    @Transactional
    @Override
    public boolean remove(String id) {
        userDao.deleteById(id);
        userRoleService.delete(new EntityWrapper<UserRole>().eq("userId", id));
        return true;
    }

    @Transactional
    @Override
    public boolean batchRemove(String[] ids) {
        List<String> list = Arrays.asList(ids);
        userDao.deleteBatchIds(list);
        list.forEach(id -> {
            userRoleService.delete(new EntityWrapper<UserRole>().eq("userId", id));
        });
        return true;
    }

    @Override
    public boolean resetPwd(String id, String password) {
        User user = selectById(id);
        user.setPassword(new SimpleHash("MD5", password, selectById(id).getUserName(), 1024).toString());
        updateById(user);
        return true;
    }

}
