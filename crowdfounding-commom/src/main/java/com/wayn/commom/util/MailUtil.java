package com.wayn.commom.util;

import com.sun.mail.util.MailSSLSocketFactory;
import com.wayn.commom.domain.MailConfig;
import com.wayn.commom.domain.vo.SendMailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {

    private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

    public static void sendMail(MailConfig mailConfig, SendMailVO mailVO) {
        try {
            // 设置发件人
            String from = mailConfig.getFromUser();
            // 设置收件人
            String to = mailVO.getSendMail();
            // 设置邮件发送的服务器，这里为QQ邮件服务器
            String host = mailConfig.getHost();
            // 获取系统属性
            Properties properties = System.getProperties();
            // SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
            // 设置系统属性
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");
            //获取发送邮件会话、获取第三方登录授权码
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, mailConfig.getPass());
                }
            });
//            session.setDebug(true);
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            // Set From: 头部头字段
            helper.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            helper.setTo(to);
            // Set Subject: 头部头字段
            helper.setSubject(mailVO.getTitle());
            // 设置消息体
            helper.setText(mailVO.getContent(), true);
            Transport.send(message);
            logger.info("邮件发送成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }
}
