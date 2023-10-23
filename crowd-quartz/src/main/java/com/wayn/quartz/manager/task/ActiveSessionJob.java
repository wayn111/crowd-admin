package com.wayn.quartz.manager.task;

import com.wayn.common.base.WebSocketResp;
import com.wayn.common.domain.UserOnline;
import com.wayn.common.domain.dto.WsUserPrincipal;
import com.wayn.common.service.UserOnlineService;
import com.wayn.common.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component("activeSessionJob")
public class ActiveSessionJob {

    public void doJob() {
        SimpMessagingTemplate simpMessagingTemplate = SpringContextUtil.getBean(SimpMessagingTemplate.class);
        UserOnlineService userOnlineService = SpringContextUtil.getBean(UserOnlineService.class);
        List<UserOnline> list = userOnlineService.list();
        Set<WsUserPrincipal> principals = userOnlineService.wsUserList();
        for (WsUserPrincipal principal : principals) {
            if (!list.stream().map(UserOnline::getUserId).toList().contains(principal.getId())) {
                simpMessagingTemplate.convertAndSendToUser(principal.getId(), "/queue/userLoginStatus", new WebSocketResp(1, 0));
                log.info("webSocket conn:{},被定时任务清理下线", principal);
            }
        }
    }
}


