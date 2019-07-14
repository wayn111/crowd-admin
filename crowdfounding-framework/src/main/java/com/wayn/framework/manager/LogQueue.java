package com.wayn.framework.manager;

import com.wayn.commom.domain.Log;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 日志处理队列
 */
@Component
public class LogQueue {
    private BlockingQueue<Log> queue = new LinkedBlockingDeque<>();

    public void add(Log log) {
        queue.add(log);
    }

    public Log take() throws InterruptedException {
        return queue.take();
    }
}
