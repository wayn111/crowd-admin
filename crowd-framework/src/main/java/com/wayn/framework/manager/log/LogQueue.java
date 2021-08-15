package com.wayn.framework.manager.log;

import com.wayn.common.domain.OperLog;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 日志处理队列
 */
@Component
public class LogQueue {
    private final BlockingQueue<OperLog> queue = new LinkedBlockingDeque<>(1000);

    public void add(OperLog log) {
        queue.add(log);
    }

    public OperLog take() throws InterruptedException {
        return queue.take();
    }

    public OperLog poll(long timeout, TimeUnit unit) throws InterruptedException {
        return queue.poll(timeout, unit);
    }
}
