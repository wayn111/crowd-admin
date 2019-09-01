package com.wayn.web.controller.profile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.User;
import com.wayn.commom.domain.vo.UserResetPasswordVO;
import com.wayn.commom.service.DeptService;
import com.wayn.commom.service.UserService;
import com.wayn.commom.util.FileUploadUtil;
import com.wayn.commom.util.HttpUtil;
import com.wayn.commom.util.ProperUtil;
import com.wayn.commom.util.Response;
import com.wayn.commom.annotation.RepeatSubmit;
import com.wayn.framework.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 个人资料处理控制器
 */
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
    @RepeatSubmit
    @ResponseBody
    @PostMapping("updateUser")
    public Response updateUser(HttpServletRequest request, User user) {
        // 更新用户信息后，浏览器端订阅的用户认证信息就不一致了，需要刷新浏览器。
        // 如果想要一致，需要修改stompEndpointRegistry添加握手处理器，将用户认证消息替换成用户id。
        ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
        servletServerHttpRequest.getPrincipal();

        userService.updateById(user);
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(userService.selectById(user.getId()), realmName);
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
    @ResponseBody
    @PostMapping("judgeOldPasswordSuccess")
    public boolean judgeOldPasswordSuccess(String oldPassword) {
        // 获取加密后的密码
        String password = ShiroUtil.md5encrypt(oldPassword, userService.selectById(getCurUserId()).getUserName());
        List<User> users = userService.selectList(new EntityWrapper<User>().eq("password", password));
        return users.size() > 0;
    }

    @ResponseBody
    @PostMapping("userResetPwd")
    public Response userResetPwd(UserResetPasswordVO userResetPasswordVO) {
        String password = ShiroUtil.md5encrypt(userResetPasswordVO.getNewPassword(), userService.selectById(getCurUserId()).getUserName());
        userService.updateForSet("password = '" + password + "'", new EntityWrapper<User>().eq("id", getCurUserId()));
        return Response.success("修改用户密码成功！");
    }


    /**
     * 更新头像照片
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("updateAvatar")
    public Response updateAvatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        // 上传文件路径
        String filePath = ProperUtil.get("wayn.uploadDir") + "/avatar";
        String fileName = FileUploadUtil.uploadFile(file, filePath);
        // Thumbnails.of(filePath + "/" + fileName).size(64, 64).toFile(new File(filePath, fileName));
        String requestUrl = HttpUtil.getRequestContext(request);
        String url = requestUrl + "/upload/avatar/" + fileName;
        userService.updateForSet("userImg = '" + url + "'", new EntityWrapper<User>().eq("id", getCurUserId()));
        // 更新subject中用户信息
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(userService.selectById(getCurUserId()), realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
        return Response.success("上传头像成功！");
    }

    /**
     * 检查用户登陆是否过期，或者已经被登出
     *
     * @return Response
     */
    @ResponseBody
    @PostMapping("auth")
    public Response auth() {
        getCurUser();
        return Response.success();
    }

}
