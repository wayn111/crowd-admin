package com.wayn.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebSocketReq implements Serializable {

    /**
     * websocket类型 1登录状态查询
     */
    private Integer type;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * webSocket连接ID
     */
    private String connId;
}
