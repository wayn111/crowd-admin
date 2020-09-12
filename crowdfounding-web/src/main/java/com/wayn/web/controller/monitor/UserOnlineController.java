package com.wayn.web.controller.monitor;

import com.wayn.commom.annotation.Log;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.domain.User;
import com.wayn.commom.domain.UserOnline;
import com.wayn.commom.enums.Operator;
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

    @Log(value = "在线用户管理", operator = Operator.LOGOUT)
    @RequiresPermissions("monitor:online:logout")
    @ResponseBody
    @DeleteMapping("/forceLogout/{id}")
    public Response forceLogout(Model model, @PathVariable("id") String id) {
        if (getSessionId().equals(id)) {
            return Response.error("当前登陆用户无法强退");
        }
        sendMsg2User(id);
        userOnlineService.forceLogout(id);
        return Response.success("强制下线用户成功！");

    }

    @Log(value = "在线用户管理", operator = Operator.LOGOUT)
    @RequiresPermissions("monitor:online:logout")
    @ResponseBody
    @PostMapping("/batchForceLogout")
    public Response batchForceLogout(@RequestParam("ids[]") String[] ids) {
        for (String id : ids) {
            if (getSessionId().equals(id)) {
                return Response.error("当前用户无法强退");
            }
            sendMsg2User(id);
            userOnlineService.forceLogout(id);
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
