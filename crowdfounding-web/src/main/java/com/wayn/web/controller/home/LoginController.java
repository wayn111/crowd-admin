package com.wayn.web.controller.home;


import com.wayn.commom.base.BaseController;
import com.wayn.commom.constant.Constants;
import com.wayn.commom.service.ConfigService;
import com.wayn.commom.service.LogininforService;
import com.wayn.commom.shiro.util.ShiroUtil;
import com.wayn.commom.util.Response;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final String PREFIX = "home";

    @Autowired
    private LogininforService logininforService;

    @Autowired
    private ConfigService configService;

    @GetMapping("/login")
    public String login(ModelMap model) {
        model.addAttribute("sysName", configService.getValueByKey("sys.name"));
        model.addAttribute("sysFooter", configService.getValueByKey("sys.footer.copyright"));
        return PREFIX + "/login";
    }

    @ResponseBody
    @PostMapping("/doLogin")
    public Response doLogin(String userName, String password, @RequestParam(defaultValue = "false") Boolean rememberMe, String clientKaptcha) {
        String kaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.CAPTCHA_SESSION_KEY);
        if (!StringUtils.equalsIgnoreCase(clientKaptcha, kaptcha)) {
            logininforService.addLog(userName, com.wayn.commom.constant.Constants.LOGIN_FAIL, "验证码错误");
            return Response.error("验证码错误");
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
        currentUser.login(token);
        logininforService.addLog(userName, com.wayn.commom.constant.Constants.LOGIN_SUCCESS, "登陆成功");
        return Response.success();
    }

    @GetMapping("/logout")
    public String logout() {
        String userName = ShiroUtil.getSessionUser().getUserName();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            logininforService.addLog(userName, com.wayn.commom.constant.Constants.LOGOUT, "退出成功");
        } catch (Exception exception) {
            logininforService.addLog(userName, com.wayn.commom.constant.Constants.LOGIN_FAIL, "退出失败：" + exception.getMessage());
            logger.error(exception.getMessage(), exception);
        }
        return redirectTo("/");
    }

    /**
     * 验证码
     *
     * @param response
     * @param session
     */
    @GetMapping(value = "/captcha")
    public void captcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(140, 54, 4);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

        // 验证码存入session
        session.setAttribute(Constants.CAPTCHA_SESSION_KEY, specCaptcha.text().toLowerCase());

        // 输出图片流
        specCaptcha.out(response.getOutputStream());

    }
}
