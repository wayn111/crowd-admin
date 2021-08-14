package com.wayn.web.controller.profile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wayn.common.annotation.Log;
import com.wayn.common.annotation.RepeatSubmit;
import com.wayn.common.base.BaseController;
import com.wayn.common.domain.Dept;
import com.wayn.common.domain.User;
import com.wayn.common.domain.vo.UserResetPasswordVO;
import com.wayn.common.enums.Operator;
import com.wayn.common.service.DeptService;
import com.wayn.common.service.UserService;
import com.wayn.common.shiro.util.ShiroUtil;
import com.wayn.common.util.FileUploadUtil;
import com.wayn.common.util.HttpUtil;
import com.wayn.common.util.ProperUtil;
import com.wayn.common.util.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 个人资料处理控制器
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController {

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
        Dept dept = deptService.getById(curUser.getDeptId());
        deptNames.add(dept.getDeptName());
        while (dept.getPid() != 0) {
            dept = deptService.getById(dept.getPid());
            deptNames.add(dept.getDeptName());
        }
        model.addAttribute("deptName", StringUtils.join(deptNames, " / "));
        return PREFIX + "/profile";
    }

    @GetMapping("/avatar")
    public String avatar(Model model) {
        model.addAttribute("imgSrc", getCurUser().getUserImg());
        return PREFIX + "/avatar";
    }

    /**
     * 更新个人资料
     *
     * @param request
     * @param user
     * @return
     */
    @Log(value = "个人信息管理", operator = Operator.UPDATE)
    @RepeatSubmit
    @ResponseBody
    @PostMapping("updateUser")
    public Response updateUser(HttpServletRequest request, User user) {
        ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
        servletServerHttpRequest.getPrincipal();
        if (!userService.updateById(user)) {
            return Response.error("更新用户信息失败");
        }
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(userService.getById(user.getId()), realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
        return Response.success("修改用户信息成功！");
    }

    /**
     * 判断旧密码是否正确
     *
     * @param oldPassword
     * @return
     */
    @Log(value = "个人信息管理", operator = Operator.UPDATE)
    @ResponseBody
    @PostMapping("judgeOldPasswordSuccess")
    public boolean judgeOldPasswordSuccess(String oldPassword) {
        // 获取加密后的密码
        String password = ShiroUtil.md5encrypt(oldPassword, userService.getById(getCurUserId()).getUserName());
        List<User> users = userService.list(new QueryWrapper<User>().eq("password", password));
        return users.size() > 0;
    }

    @Log(value = "个人信息管理", operator = Operator.UPDATE)
    @ResponseBody
    @RequiresPermissions("sys:user:resetPwd")
    @PostMapping("userResetPwd")
    public Response userResetPwd(UserResetPasswordVO userResetPasswordVO) {
        String password = ShiroUtil.md5encrypt(userResetPasswordVO.getNewPassword(), userService.getById(getCurUserId()).getUserName());
        boolean update = userService.update().set("password", password).eq("id", getCurUserId()).update();
        if (!update) {
            return Response.error("更新用户密码失败");
        }
        return Response.success("修改用户密码成功！");
    }


    /**
     * 更新头像照片
     *
     * @param file
     * @return
     */
    @Log(value = "个人信息管理", operator = Operator.UPDATE)
    @ResponseBody
    @PostMapping("updateAvatar")
    public Response updateAvatar(@RequestParam("avatarfile") MultipartFile file) {
        // 上传文件路径
        String filePath = ProperUtil.get("wayn.uploadDir") + "/avatar";
        String fileName = FileUploadUtil.uploadFile(file, filePath);
        // Thumbnails.of(filePath + "/" + fileName).size(64, 64).toFile(new File(filePath, fileName));
        String requestUrl = HttpUtil.getRequestContext(request);
        String url = requestUrl + "/upload/avatar/" + fileName;
        boolean update = userService.update().set("userImg", url).eq("id", getCurUserId()).update();
        if (!update) {
            return Response.error("更新用户头像失败");
        }
        // 更新subject中用户信息
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(userService.getById(getCurUserId()), realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
        return Response.success("上传头像成功！").add("imgSrc", url);
    }

    /**
     * 检查用户登陆是否过期，或者已经被登出
     *
     * @return Response
     */
    @Log(value = "个人信息管理", operator = Operator.UPDATE)
    @ResponseBody
    @PostMapping("auth")
    public Response auth() {
        getCurUser();
        return Response.success();
    }

}
