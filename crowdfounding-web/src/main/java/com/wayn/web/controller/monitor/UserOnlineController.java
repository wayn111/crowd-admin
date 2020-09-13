package com.wayn.web.controller.monitor;

import com.wayn.commom.annotation.Log;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.constant.Constant;
import com.wayn.commom.domain.User;
import com.wayn.commom.domain.UserOnline;
import com.wayn.commom.service.LogininforService;
import com.wayn.commom.service.UserOnlineService;
import com.wayn.commom.util.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/monitor/online")
@Controller
public class UserOnlineController extends BaseControlller {
    private static final String PREFIX = "monitor/online";
    @Autowired
    private UserOnlineService userOnlineService;

    @Autowired
    private LogininforService logininforService;

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @RequiresPermissions("monitor:online:online")
    @GetMapping
    public String onlineIndex() {
        return PREFIX + "/online";
    }

    @Log(value = "在线用户管理")
    @RequiresPermissions("monitor:online:online")
    @ResponseBody
    @PostMapping("/list")
    public List<UserOnline> list() {
        return userOnlineService.list();
    }

    @RequiresPermissions("monitor:online:logout")
    @ResponseBody
    @DeleteMapping("/forceLogout/{id}")
    public Response forceLogout(Model model, @PathVariable("id") String id) {
        String userName = userOnlineService.getUserName(id);
        if (getSessionId().equals(id)) {
            return Response.error("当前登陆用户无法强退");
        }
        try {
            userOnlineService.forceLogout(id);
            sendMsg2User(id);
            logininforService.addLog(userName, Constant.LOGIN_SUCCESS, "后台强制退出 " + userName + " 成功");
        } catch (Exception exception) {
            logininforService.addLog(userName, Constant.LOGIN_FAIL, "后台强制退出 " + userName + " 失败");

        }
        return Response.success("强制下线用户成功！");

    }

    @RequiresPermissions("monitor:online:logout")
    @ResponseBody
    @PostMapping("/batchForceLogout")
    public Response batchForceLogout(@RequestParam("ids[]") String[] ids) {
        for (String id : ids) {
            if (getSessionId().equals(id)) {
                return Response.error("当前用户无法强退");
            }
            String userName = userOnlineService.getUserName(id);
            try {
                userOnlineService.forceLogout(id);
                sendMsg2User(id);
                logininforService.addLog(userName, Constant.LOGIN_SUCCESS, "后台强制退出 " + userName + " 成功");
            } catch (Exception exception) {
                logininforService.addLog(userName, Constant.LOGIN_FAIL, "后台强制退出 " + userName + " 失败");

            }
        }
        return Response.success("强制下线用户成功！");

    }

    /**
     * 推送消息给指定用户
     *
     * @param id sessionId
     */
    public void sendMsg2User(String id) {
        Session session = sessionDAO.readSession(id);
        if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null) {
            SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                    .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
            User user = (User) primaryPrincipal;
            simpMessagingTemplate.convertAndSendToUser(user.getId(), "/queue/getResponse", "新消息：" + "该账号已被强制登出！");
        }
    }
}
