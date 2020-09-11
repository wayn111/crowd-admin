package com.wayn.commom.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.dao.DeptDao;
import com.wayn.commom.dao.UserDao;
import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.User;
import com.wayn.commom.domain.UserRole;
import com.wayn.commom.domain.vo.Tree;
import com.wayn.commom.excel.IExcelExportStylerImpl;
import com.wayn.commom.service.UserRoleService;
import com.wayn.commom.service.UserService;
import com.wayn.commom.shiro.util.ShiroUtil;
import com.wayn.commom.util.FileUtils;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.util.TreeBuilderUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private UserDao userDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private UserRoleService userRoleService;

    @Value("${wayn.uploadDir}")
    private String uploadDir;

    @Override
    public Page<User> listPage(Page<User> page, User user) {
        EntityWrapper<User> wrapper = ParameterUtil.get();
        wrapper.like("userName", user.getUserName());
        wrapper.like("phone", user.getPhone());
        wrapper.like("email", user.getEmail());
        wrapper.eq(user.getUserState() != null, "userState", user.getUserState());
        wrapper.eq(user.getDeptId() != null, "deptId", user.getDeptId());
        return selectPage(page, wrapper);
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

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional
    @Override
    public boolean save(User user, String roleIds) {
        user.setCreateTime(new Date());
        user.setPassword(new SimpleHash("MD5", user.getPassword(), user.getUserName(), 1024).toString());
        boolean flag = insert(user);
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
        userRoleService.delete(new EntityWrapper<UserRole>().eq("userId", user.getId()));
        if (list.size() > 0) {
            userRoleService.insertBatch(list);
        }
        return flag;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional
    @Override
    public boolean update(User user, String roleIds) {
        boolean flag = updateById(user);
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
        list.forEach(id -> userRoleService.delete(new EntityWrapper<UserRole>().eq("userId", id)));
        return true;
    }

    @Override
    public boolean resetPwd(String id, String password) {
        updateForSet("password = '" + password + "'", new EntityWrapper<User>().eq("id", id));
        return true;
    }

    @Override
    public boolean editAcount(String id, String userName) {
        updateForSet("userName = '" + userName + "', password ='" + ShiroUtil.md5encrypt("123456", userName) + "'",
                new EntityWrapper<User>().eq("id", id));
        return true;
    }

    @Override
    public Tree<Dept> getTree() {
        List<Tree<Dept>> trees = new ArrayList<>();
        List<Dept> depts = deptDao.selectList(new EntityWrapper<>());
        List<User> users = userDao.selectList(new EntityWrapper<>());
        // 获取部门所有pid
        List<Long> ds = depts.stream().map(Dept::getPid).distinct().collect(Collectors.toList());
        // 获取用户所有deptId
        List<Long> us = users.stream().map(User::getDeptId).distinct().collect(Collectors.toList());
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
        List<User> users = selectList(new EntityWrapper<>());
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
        EntityWrapper<User> wrapper = ParameterUtil.get();
        wrapper.like("userName", user.getUserName());
        wrapper.like("phone", user.getPhone());
        wrapper.like("email", user.getEmail());
        wrapper.eq(user.getUserState() != null, "userState", user.getUserState());
        wrapper.eq(user.getDeptId() != null, "deptId", user.getDeptId());
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        List<User> list = selectList(wrapper);
        list.forEach(item -> item.setUserImg(uploadDir + item.getUserImg().substring(item.getUserImg().indexOf("upload") + 6)));
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
                User.class, list);
        // 使用bos获取excl文件大小
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Length", bos.size() + "");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, "用户列表.xls"));
        response.setContentType("application/octet-stream;charset=UTF-8");
        //保存数据
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.close();
        bos.close();
    }

}
