package com.wayn.common.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * ip2region帮助类
 */
@Slf4j
@Component
public class Ip2Region {

    private Searcher searcher = null;

    @Value("${wayn.ip2region.path:}")
    private String ip2regionPath = "D:/ip2region.xdb";

    @PostConstruct
    private void init() {
        // 1、从 dbPath 加载整个 xdb 到内存。
        String dbPath = ip2regionPath;

        // 2、从 dbPath 加载整个 xdb 到内存。
        byte[] cBuff;
        try {
            cBuff = Searcher.loadContentFromFile(dbPath);
            searcher = Searcher.newWithBuffer(cBuff);
        } catch (Exception e) {
            log.error("找不到ip2region.xdb文件，请指定正确加载路径，{}", e.getMessage(), e);
        }
    }

    @PreDestroy
    private void destroy() throws IOException {
        if (searcher != null) {
            searcher.close();
            log.info("ip2region searcher destroy success!");
        }
    }

    public String getCity(String ip) {
        String region = this.parseIpInfo(ip);
        String[] split = region.split("\\|");
        String country = split[0];
        String province = split[2];
        String city = split[3];
        String isp = split[4];
        return city;
    }

    public String parseIpInfo(String ip) {

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

}
