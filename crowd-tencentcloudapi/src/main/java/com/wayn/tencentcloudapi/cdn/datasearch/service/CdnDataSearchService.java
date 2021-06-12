package com.wayn.tencentcloudapi.cdn.datasearch.service;

import com.tencentcloudapi.cdn.v20180606.CdnClient;

/**
 * cdn数据查询接口
 */
public interface CdnDataSearchService {

    /**
     * top数据查询
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param metric    排序使用的指标名称
     * @param filter    Get 排序使用的指标名称
     * @param <T>       泛型返回类型
     * @return 返回指定泛型
     */
    <T> T topDataSearch(String startTime, String endTime, String metric, String filter);

    /**
     * 访问数据查询
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param metric    排序使用的指标名称
     * @param <T>       泛型返回类型
     * @return 返回指定泛型
     */
    <T> T accessDataSearch(String startTime, String endTime, String metric);
}
