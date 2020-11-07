package com.wayn.quartz.manager.task;

import com.wayn.commom.service.ConfigService;
import com.wayn.commom.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component("welComeJob")
public class WelComeJob {

    private static final Logger logger = LoggerFactory.getLogger(WelComeJob.class);

    @Autowired
    private ConfigService configService;

    public void welCome() {
        SimpMessagingTemplate simpMessagingTemplate = SpringContextUtil.getBean(SimpMessagingTemplate.class);
        String sysName = configService.getValueByKey("sys.name");
        simpMessagingTemplate.convertAndSend("/topic/getResponse", "WelCome to " + sysName + "！");
        logger.info("执行自定义定时任务");
    }

    public void test(String a, Integer b) {
        logger.info("welComeJob ----- test({},{})", a, b);
    }
}


