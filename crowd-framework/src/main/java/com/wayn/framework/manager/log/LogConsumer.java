package com.wayn.framework.manager.log;

import com.wayn.commom.domain.OperLog;
import com.wayn.commom.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志异步处理线程
 */
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
                OperLog log = queue.take();
                temp.add(log);
            }
            if (temp.size() != 0) {
                logService.saveBatch(temp);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
