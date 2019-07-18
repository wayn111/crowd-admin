package com.wayn.web.controller.home;

import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.Response;
import com.wayn.framework.annotation.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class LoginController extends BaseControlller {

    private static final String PREFIX = "home";

    @Autowired
    private SessionDAO sessionDAO;

    @GetMapping("/login")
    public String login(ModelMap map) {
        return PREFIX + "/login";
    }

    @Log(value = "系统登陆")
    @ResponseBody
    @PostMapping("/doLogin")
    public Response doLogin(String userName, String password, HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        if (!currentUser.isAuthenticated()) {
            //token.setRememberMe(true);
            currentUser.login(token);
        }
        return Response.success();
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        //sessionDAO.delete(subject.getSession(false));
        return redirectTo("/");
    }
}
