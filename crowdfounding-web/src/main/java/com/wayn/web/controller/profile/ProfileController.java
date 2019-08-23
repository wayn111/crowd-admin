package com.wayn.web.controller.profile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.User;
import com.wayn.commom.domain.vo.UserResetPasswordVO;
import com.wayn.commom.service.DeptService;
import com.wayn.commom.service.UserService;
import com.wayn.commom.util.Response;
import com.wayn.framework.annotation.RepeatSubmit;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseControlller {

    private static final String PREFIX = "profile";

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(Model model) {
        User curUser = getCurUser();
        model.addAttribute("user", curUser);
        List<String> deptNames = new ArrayList<>();
        Dept dept = deptService.selectById(curUser.getDeptId());
        deptNames.add(dept.getDeptName());
        while (dept.getPid() != 0) {
            dept = deptService.selectById(dept.getPid());
            deptNames.add(dept.getDeptName());
        }
        model.addAttribute("deptName", StringUtils.join(deptNames, " / "));
        return PREFIX + "/profile";
    }

    @RepeatSubmit
    @ResponseBody
    @PostMapping("updateUser")
    public Response updateUser(Model model, User user) {
        userService.updateById(user);
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection =
                new SimplePrincipalCollection(userService.selectById(user.getId()), realmName);
        subject.runAs(newPrincipalCollection);
        return Response.success("修改用户信息成功！");
    }

    @ResponseBody
    @PostMapping("oldPasswordJudge")
    public boolean oldPasswordJudge(Model model, String oldPassword) {
        // 获取加密后的密码
        String password = new SimpleHash("MD5", oldPassword, userService.selectById(getCurUserId()).getUserName(), 1024).toString();
        List<User> users = userService.selectList(new EntityWrapper<User>().eq("password", password));
        return users.size() > 0 ? true : false;
    }

    @ResponseBody
    @PostMapping("userResetPwd")
    public Response userResetPwd(Model model, UserResetPasswordVO userResetPasswordVO) {
        String password = new SimpleHash("MD5", userResetPasswordVO.getNewPassword(), userService.selectById(getCurUserId()).getUserName(), 1024).toString();
        userService.updateForSet("password = '" + password + "'", new EntityWrapper<User>().eq("id", getCurUserId()));
        return Response.success("修改用户密码成功！");
    }
}
