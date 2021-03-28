package com.wayn.commom.enums.tencentcloud.cdn;

public enum MetricEnum {
    URL, // 访问 URL 排序（无参数的URL），支持的 Filter 为 flux、request
    DISTRICT, // 省份、国家/地区排序，支持的 Filter 为 flux、request
    ISP, // 运营商排序，支持的 Filter 为 flux、request
    HOST, // 域名访问数据排序，支持的 Filter 为：flux、request、bandwidth、fluxHitRate、2XX、3XX、4XX、5XX、statusCode
    ORIGIN_HOST;// 域名回源数据排序，支持的 Filter 为 flux、request、bandwidth、origin_2XX、origin_3XX、origin_4XX、origin_5XX、OriginStatusCode


    public String getLowerName() {
        return this.name().toLowerCase();
    }

}
