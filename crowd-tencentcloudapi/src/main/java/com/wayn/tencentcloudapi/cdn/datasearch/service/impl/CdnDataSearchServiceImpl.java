package com.wayn.tencentcloudapi.cdn.datasearch.service.impl;

import com.tencentcloudapi.cdn.v20180606.CdnClient;
import com.tencentcloudapi.cdn.v20180606.models.DescribeCdnDataRequest;
import com.tencentcloudapi.cdn.v20180606.models.ListTopDataRequest;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.wayn.tencentcloudapi.cdn.datasearch.service.CdnDataSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CdnDataSearchServiceImpl implements CdnDataSearchService {

    private static final Logger logger = LoggerFactory.getLogger(CdnDataSearchServiceImpl.class);

    @Autowired
    private CdnClient cdnClient;

    @Override
    public <T> T topDataSearch(String startTime, String endTime, String metric, String filter) {
        try {
            ListTopDataRequest req = new ListTopDataRequest();
            req.setStartTime("2021-03-22 00:00:00");
            req.setEndTime("2021-03-28 15:00:00");
            req.setMetric(metric);
            req.setFilter(filter);
            return (T) cdnClient.ListTopData(req);
        } catch (TencentCloudSDKException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public <T> T accessDataSearch(String startTime, String endTime, String metric) {
        try {
            DescribeCdnDataRequest req = new DescribeCdnDataRequest();
            req.setStartTime(startTime);
            req.setEndTime(endTime);
            req.setMetric(metric);
            return (T) cdnClient.DescribeCdnData(req);
        } catch (TencentCloudSDKException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
