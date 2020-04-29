package com.wayn.web.controller.commom;

import com.wayn.commom.domain.MailConfig;
import com.wayn.commom.domain.vo.SendMailVO;
import com.wayn.commom.service.MailConfigService;
import com.wayn.commom.util.MailUtil;
import com.wayn.commom.util.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/commom/mail")
public class MailController {
    private static final String PREFIX = "commom/mail";

    @Autowired
    private MailConfigService mailConfigService;

    @GetMapping
    public String index(HttpServletRequest request) {
        request.setAttribute("mail", mailConfigService.selectById(1L));
        return PREFIX + "/mail";
    }

    @ResponseBody
    @PostMapping("update")
    public Response update(MailConfig mailConfig) {
        mailConfig.setId(1L);
        mailConfigService.updateById(mailConfig);
        return Response.success("修改成功");
    }

    @ResponseBody
    @PostMapping("sendMail")
    public Response sendMail(SendMailVO mailVO) {
        MailConfig mailConfig = mailConfigService.selectById(1L);
        if (StringUtils.isEmpty(mailConfig.getFromUser())
                || StringUtils.isEmpty(mailConfig.getHost())
                || StringUtils.isEmpty(mailConfig.getPass())) {
            return Response.error("邮件未配置，请先填写配置信息");
        }
        MailUtil.sendMail(mailConfig, mailVO);
        return Response.success("发送成功");
    }
}
