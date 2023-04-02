package com.wayn.common.util;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.util.concurrent.TimeUnit;

/**
 * ip2region帮助类
 */
@Slf4j
public class IP2RegionUtil {
    public static String getCityInfo(String ip) {

        String dbPath = "D:/ip2region.xdb";

        // 1、从 dbPath 加载整个 xdb 到内存。
        byte[] cBuff = new byte[0];
        try {
            cBuff = Searcher.loadContentFromFile(dbPath);
        } catch (Exception e) {
            log.error("failed to load content from `{}`", dbPath, e);
        }

        // 2、使用上述的 cBuff 创建一个完全基于内存的查询对象。
        Searcher searcher;
        try {
            searcher = Searcher.newWithBuffer(cBuff);
        } catch (Exception e) {
            log.error("failed to create content cached searcher", e);
            return null;
        }

        // 3、查询
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - sTime);
            log.info("{region: {}, ioCount: {}, took: {} μs}", region, searcher.getIOCount(), cost);
            return region;
        } catch (Exception e) {
            log.error("failed to search({})", ip, e);
        }

        // 4、关闭资源 - 该 searcher 对象可以安全用于并发，等整个服务关闭的时候再关闭 searcher
        // searcher.close();

        // 备注：并发使用，用整个 xdb 数据缓存创建的查询对象可以安全的用于并发，也就是你可以把这个 searcher 对象做成全局对象去跨线程访问。
        return null;
    }

    public static void main(String[] args) throws Exception {
        getCityInfo("121.4.124.33");
    }

}
