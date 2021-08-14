package com.wayn.common.enums.tencentcloud.cdn;

public enum FilterEnum {
    FLUX, //Metric 为 host 时指代访问流量，originHost 时指代回源流量
    BANDWIDTH,//Metric 为 host 时指代访问带宽，originHost 时指代回源带宽
    REQUEST,//Metric 为 host 时指代访问请求数，originHost 时指代回源请求数
    FLUXHITRATE,//平均流量命中率
    STATUSCODE,//指定访问状态码统计，在 Code 参数中填充指定状态码
    ORIGINSTATUSCODE;//指定回源状态码统计，在 Code 参数中填充指定状态码

    public String getLowerName() {
        return this.name().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(FLUX.getLowerName());
    }
}
