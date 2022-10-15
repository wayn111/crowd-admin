// package com.wayn.web.controller.common;
//
// import com.wayn.common.domain.MailConfig;
// import com.wayn.common.domain.vo.SendMailVO;
// import com.wayn.common.service.MailConfigService;
// import com.wayn.common.util.Response;
// import com.wayn.framework.jms.queue.MailQueueProducer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// import javax.servlet.http.HttpServletRequest;
//
// @Controller
// @RequestMapping("/common/mail")
// public class MailController {
//     private static final String PREFIX = "common/mail";
//
//     @Autowired
//     private MailConfigService mailConfigService;
//
//     @Autowired
//     private MailQueueProducer mailQueueProducer;
//
//     @GetMapping
//     public String index(HttpServletRequest request) {
//         request.setAttribute("mail", mailConfigService.getById(1L));
//         return PREFIX + "/mail";
//     }
//
//     @ResponseBody
//     @PostMapping("update")
//     public Response update(MailConfig mailConfig) {
//         mailConfigService.saveOrUpdate(mailConfig);
//         return Response.success("修改成功");
//     }
//
//     @ResponseBody
//     @PostMapping("sendMail")
//     public Response sendMail(SendMailVO mailVO) {
//         MailConfig mailConfig = mailConfigService.getById(1L);
//         if (!mailConfigService.checkMailConfig(mailConfig)) {
//             return Response.error("邮件信息未配置完全，请先填写配置信息");
//         }
//         // mailQueueProducer.sendMail(mailConfig, mailVO);
//         return Response.success("发送成功，请等待");
//     }
// }
