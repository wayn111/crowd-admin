package com.wayn.framework.manager.log;

import com.wayn.common.domain.OperLog;
import com.wayn.common.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 日志异步处理线程
 */
@Slf4j
@Component
public class LogConsumer implements Runnable {

    @Autowired
    private LogQueue queue;

    /**
     * 日志临时存储总数
     */
    @Value("${wayn.logHandlerTempNum}")
    private Integer logHandlerNum;

    @Autowired
    private LogService logService;

    @PostConstruct
    public void init() {
        new Thread(this, "logAsyncHandlerThread").start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            logHandler();
        }
    }

    private void logHandler() {
        List<OperLog> temp = new ArrayList<>();
        try {
            while (temp.size() <= logHandlerNum) {
                OperLog log = queue.poll(2000, TimeUnit.MILLISECONDS);
                if (log != null) {
                    temp.add(log);
                }
            }
            if (!temp.isEmpty()) {
                logService.saveBatch(temp);
                temp.clear();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
