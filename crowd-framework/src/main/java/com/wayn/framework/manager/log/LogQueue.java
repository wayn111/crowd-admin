package com.wayn.framework.manager.log;

import com.wayn.common.domain.OperLog;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 日志处理队列
 */
@Component
public class LogQueue {
    private BlockingQueue<OperLog> queue = new LinkedBlockingDeque<>();

    public void add(OperLog log) {
        queue.add(log);
    }

    public OperLog take() throws InterruptedException {
        return queue.take();
    }
}
