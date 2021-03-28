package com.wayn.tencentcloudapi.cdn.datasearch.service;

import com.tencentcloudapi.cdn.v20180606.CdnClient;

public interface CdnDataSearchService {


    CdnClient getCdnClient();
    /**
     * top数据查询
     * @param startTime
     * @param endTime
     * @param metric
     * @param filter
     * @param <T>
     * @return
     */
    <T> T topDataSearch(String startTime, String endTime, String metric, String filter);

    /**
     * 访问数据查询
     * @param startTime
     * @param endTime
     * @param metric
     * @param <T>
     * @return
     */
    <T> T accessDataSearch(String startTime, String endTime, String metric);
}
