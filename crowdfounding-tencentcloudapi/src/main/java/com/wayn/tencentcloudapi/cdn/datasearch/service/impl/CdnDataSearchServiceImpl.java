package com.wayn.tencentcloudapi.cdn.datasearch.service.impl;

import com.tencentcloudapi.cdn.v20180606.CdnClient;
import com.tencentcloudapi.cdn.v20180606.models.DescribeCdnDataRequest;
import com.tencentcloudapi.cdn.v20180606.models.ListTopDataRequest;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.wayn.tencentcloudapi.cdn.datasearch.service.CdnDataSearchService;
import com.wayn.tencentcloudapi.config.CredentialConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CdnDataSearchServiceImpl implements CdnDataSearchService {

    private static final Logger logger = LoggerFactory.getLogger(CdnDataSearchServiceImpl.class);

    @Override
    public CdnClient getCdnClient() {
        Credential cred = new Credential(CredentialConfig.getSecretId(), CredentialConfig.getSecretKey());

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(CredentialConfig.getHost());

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        return new CdnClient(cred, "", clientProfile);
    }

    @Override
    public <T> T topDataSearch(String startTime, String endTime, String metric, String filter) {
        try {
            CdnClient client = getCdnClient();

            ListTopDataRequest req = new ListTopDataRequest();
            req.setStartTime("2021-03-22 00:00:00");
            req.setEndTime("2021-03-28 15:00:00");
            req.setMetric(metric);
            req.setFilter(filter);
            return (T) client.ListTopData(req);
        } catch (TencentCloudSDKException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public <T> T accessDataSearch(String startTime, String endTime, String metric) {
        try {

            CdnClient client = getCdnClient();

            DescribeCdnDataRequest req = new DescribeCdnDataRequest();
            req.setStartTime(startTime);
            req.setEndTime(endTime);
            req.setMetric(metric);

            return (T) client.DescribeCdnData(req);

        } catch (TencentCloudSDKException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}