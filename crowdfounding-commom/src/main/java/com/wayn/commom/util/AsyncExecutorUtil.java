package com.wayn.commom.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步执行帮助类
 */
public class AsyncExecutorUtil {

    private static Timer timer = null;

    static {
        if (timer == null) {
            timer = new Timer("notify-publish-thread");
        }
    }


    public static void executor(Runnable r) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        executor.execute(r);
        executor.shutdown();
    }

    public static void scheduled(TimerTask task, Date date) {
        timer.scheduleAtFixedRate(task, date, 10000);
    }


    public static void tiemrDestroyed() {
        timer.cancel();
    }
}
