package com.wayn.common.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.common.dao.DeptDao;
import com.wayn.common.dao.UserDao;
import com.wayn.common.domain.Dept;
import com.wayn.common.domain.User;
import com.wayn.common.domain.UserRole;
import com.wayn.common.domain.vo.Tree;
import com.wayn.common.domain.vo.UserVO;
import com.wayn.common.excel.IExcelExportStylerImpl;
import com.wayn.common.exception.BusinessException;
import com.wayn.common.service.UserRoleService;
import com.wayn.common.service.UserService;
import com.wayn.common.shiro.util.ShiroUtil;
import com.wayn.common.util.ParameterUtil;
import com.wayn.common.util.ProjectConfig;
import com.wayn.common.util.ServletUtil;
import com.wayn.common.util.TreeBuilderUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private UserDao userDao;
    @Resource
    private DeptDao deptDao;
    @Resource
    private UserRoleService userRoleService;

    @Resource
    private ProjectConfig projectConfig;

    @Override
    public Page<UserVO> listPage(Page<User> page, User user) {
        QueryWrapper<User> wrapper = ParameterUtil.get();
        wrapper.like("userName", user.getUserName());
        wrapper.like("phone", user.getPhone());
        wrapper.like("email", user.getEmail());
        wrapper.eq(user.getUserState() != null, "userState", user.getUserState());
        wrapper.eq(user.getDeptId() != null, "deptId", user.getDeptId());
        Page<User> userPage = userDao.selectPage(page, wrapper);
        List<User> records = userPage.getRecords();
        List<UserVO> collect = records.stream().map(user1 -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user1, userVO);
            userVO.setDeptName(deptDao.selectById(userVO.getDeptId()).getDeptName());
            return userVO;
        }).collect(Collectors.toList());
        Page<UserVO> userVOPage = new Page<>();
        userVOPage.setRecords(collect);
        userVOPage.setCurrent(userPage.getCurrent());
        userVOPage.setSize(userPage.getSize());
        userVOPage.setTotal(userPage.getTotal());
        return userVOPage;
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
        Long count = userDao.selectCount(new QueryWrapper<User>().eq("userName", userName));
        return count > 0;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional
    @Override
    public boolean save(User user, String roleIds) {
        user.setCreateTime(new Date());
        user.setPassword(new SimpleHash("MD5", user.getPassword(), user.getUserName(), 1024).toString());
        boolean save = save(user);
        if (!save) {
            throw new BusinessException("保存用户失败");
        }
        List<UserRole> list = new ArrayList<>();
        if (StringUtils.isNotBlank(roleIds)) {
            String[] split = roleIds.split(",");
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getId());
                list.add(userRole);
            }
        }
        userRoleService.remove(new QueryWrapper<UserRole>().eq("userId", user.getId()));
        return list.size() == 0 || userRoleService.saveBatch(list);
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional
    @Override
    public boolean update(User user, String roleIds) {
        List<UserRole> list = new ArrayList<>();
        if (StringUtils.isNotBlank(roleIds)) {
            String[] split = roleIds.split(",");
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getId());
                list.add(userRole);
            }
        }
        userRoleService.remove(new QueryWrapper<UserRole>().eq("userId", user.getId()));
        if (list.size() > 0) {
            userRoleService.saveBatch(list);
        }
        return updateById(user);
    }

    @Transactional
    @Override
    public boolean remove(String id) {
        userRoleService.remove(new QueryWrapper<UserRole>().eq("userId", id));
        return removeById(id);
    }

    @Transactional
    @Override
    public boolean batchRemove(String[] ids) {
        List<String> list = Arrays.asList(ids);
        userRoleService.remove(new QueryWrapper<UserRole>().in("userId", list));
        return removeByIds(list);
    }

    @Override
    public boolean resetPwd(String id, String password) {
        return update().set("password", password).eq("id", id).update();
    }

    @Override
    public boolean editAccount(String id, String userName) {
        return update().set("userName", userName)
                .set("password", ShiroUtil.md5encrypt("123456", userName))
                .eq("id", id).update();

    }

    @Override
    public Tree<Dept> getTree() {
        List<Tree<Dept>> trees = new ArrayList<>();
        List<Dept> depts = deptDao.selectList(new QueryWrapper<>());
        List<User> users = userDao.selectList(new QueryWrapper<>());
        // 获取部门所有pid
        List<Long> ds = depts.stream().map(Dept::getPid).distinct().toList();
        // 获取用户所有deptId
        List<Long> us = users.stream().map(User::getDeptId).distinct().toList();
        // 合并部门pid和用户deptId
        Long[] objects = ds.toArray(new Long[]{});
        Long[] objects1 = us.toArray(new Long[]{});
        Long[] allDepts = ArrayUtils.addAll(objects, objects1);
        for (Dept dept : depts) {
            // 过滤掉无用户挂载的部门
            if (!ArrayUtils.contains(allDepts, dept.getId())) {
                continue;
            }
            Tree<Dept> tree = new Tree<>();
            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getPid().toString());
            tree.setText(dept.getDeptName());
            tree.setType("dir");
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        for (User user : users) {
            Tree<Dept> tree = new Tree<>();
            tree.setId(user.getId());
            tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getUserName());
            tree.setType("file");
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        return TreeBuilderUtil.build(trees);
    }

    @Override
    public List<JSONObject> selectUser2JsonObj() {
        List<User> users = list(new QueryWrapper<>());
        List<JSONObject> list = new ArrayList<>();
        for (User user : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("text", user.getUserName());
            list.add(jsonObject);
        }
        return list;
    }

    @Override
    public void export(User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        QueryWrapper<User> wrapper = ParameterUtil.get();
        wrapper.like("userName", user.getUserName());
        wrapper.like("phone", user.getPhone());
        wrapper.like("email", user.getEmail());
        wrapper.eq(user.getUserState() != null, "userState", user.getUserState());
        wrapper.eq(user.getDeptId() != null, "deptId", user.getDeptId());
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        List<User> list = list(wrapper);
        list.forEach(item -> item.setUserImg(projectConfig.getUploadDir() + item.getUserImg().substring(item.getUserImg().indexOf("upload") + 6)));
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, list);
        // 使用bos获取excl文件大小
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        ServletUtil.setExportResponse(request, response, "用户列表.xls", bos.size());
        // 保存数据
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.close();
        bos.close();
    }

}
