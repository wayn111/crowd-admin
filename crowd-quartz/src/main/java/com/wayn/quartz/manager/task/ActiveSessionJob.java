package com.wayn.quartz.manager.task;

import com.wayn.common.base.WebSocketResp;
import com.wayn.common.domain.UserOnline;
import com.wayn.common.service.UserOnlineService;
import com.wayn.common.util.SpringContextUtil;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("activeSessionJob")
public class ActiveSessionJob {

    private static final Logger logger = LoggerFactory.getLogger(ActiveSessionJob.class);

    public void doJob() {
        SimpMessagingTemplate simpMessagingTemplate = SpringContextUtil.getBean(SimpMessagingTemplate.class);
        UserOnlineService userOnlineService = SpringContextUtil.getBean(UserOnlineService.class);
        List<UserOnline> list = userOnlineService.list();
        Map<String, Object> activeMap = userOnlineService.getActiveMap();
        for (String connId : activeMap.keySet()) {
            if (!list.stream().map(UserOnline::getUserId).toList().contains(connId)) {
                simpMessagingTemplate.convertAndSendToUser(connId, "/queue/getResponse", new WebSocketResp(1, 0));
                logger.info("webSocket conn:{},被定时任务清理下线", activeMap.get(connId));
            }
        }
    }

    public void test(String a, Integer b) {
        logger.info("welComeJob 欢迎----- test({},{})", a, b);
    }

}


