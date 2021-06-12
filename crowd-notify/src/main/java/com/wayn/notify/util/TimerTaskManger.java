package com.wayn.notify.util;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务帮助类
 */
public class TimerTaskManger {

    private static Map<String, TimerTask> map = new ConcurrentHashMap<>();

    public static void remove(String key) {
        map.remove(key);
    }

    public static void put(String key, TimerTask value) {
        map.put(key, value);
    }

    public static TimerTask get(String key) {
        return map.get(key);
    }

    public static boolean contains(String kek) {
        return map.containsKey(kek);
    }
}
