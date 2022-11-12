package com.wayn.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebSocketResp implements Serializable {

    /**
     * websocket类型 1登录状态查询
     */
    private Integer type;

    /**
     * 登录状态  0未登录 1已登录
     */
    private Integer loginStatus;

    public WebSocketResp(Integer type, Integer loginStatus) {
        this.type = type;
        this.loginStatus = loginStatus;
    }

}
