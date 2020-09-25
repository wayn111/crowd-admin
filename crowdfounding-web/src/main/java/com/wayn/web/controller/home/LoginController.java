package com.wayn.web.controller.home;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.constant.Constant;
import com.wayn.commom.exception.BusinessException;
import com.wayn.commom.service.ConfigService;
import com.wayn.commom.service.LogininforService;
import com.wayn.commom.shiro.util.ShiroUtil;
import com.wayn.commom.util.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class LoginController extends BaseControlller {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final String PREFIX = "home";

    @Autowired
    private Producer producer;

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
    public Response doLogin(String userName, String password, String clienkaptcha) {
        String kaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!StringUtils.equalsIgnoreCase(clienkaptcha, kaptcha)) {
            logininforService.addLog(userName, Constant.LOGIN_FAIL, "验证码错误");
            return Response.error("验证码错误");
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        if (!currentUser.isAuthenticated()) {
            //token.setRememberMe(true);
            currentUser.login(token);
            logininforService.addLog(userName, Constant.LOGIN_SUCCESS, "登陆成功");
        }
        return Response.success();
    }

    @GetMapping("/logout")
    public String logout() {
        String userName = ShiroUtil.getSessionUser().getUserName();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            logininforService.addLog(userName, Constant.LOGOUT, "退出成功");
        } catch (Exception exception) {
            logininforService.addLog(userName, Constant.LOGIN_FAIL, "退出失败：" + exception.getMessage());
            logger.error(exception.getMessage(), exception);
        }
        return redirectTo("/");
    }

    /**
     * 验证码
     *
     * @param rsp
     * @param session
     */
    @GetMapping(value = "/captcha")
    public void captcha(HttpServletResponse rsp, HttpSession session) {
        try (ServletOutputStream out = rsp.getOutputStream()) {
            rsp.setDateHeader("Expires", 0);
            rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
            rsp.setHeader("Pragma", "no-cache");
            rsp.setContentType("image/jpeg");

            String capText = producer.createText();
            //将验证码存入shiro 登录用户的session
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
            BufferedImage image = producer.createImage(capText);
            ImageIO.write(image, "jpg", out);
            out.flush();
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }

    }
}
