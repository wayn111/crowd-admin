package com.wayn.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.Response;

@Controller
@RequestMapping("/home")
public class LoginController extends BaseControlller {

	private static final String PREFIX = "home";

	@GetMapping("/login")
	public String login(ModelMap map) {
		return PREFIX + "/login";
	}

	@ResponseBody
	@PostMapping("/doLogin")
	public Response doLogin(String userName, String password, HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		if (!currentUser.isAuthenticated()) {
			// token.setRememberMe(true);
			currentUser.login(token);
		}
		return Response.success();
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		SecurityUtils.getSubject().logout();
		return PREFIX + "/login";
	}
}
