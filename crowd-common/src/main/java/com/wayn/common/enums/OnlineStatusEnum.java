package com.wayn.common.enums;

/**
 * 用户会话
 */
public enum OnlineStatusEnum {
    /**
     * 用户状态
     */
    ON_LINE(1, "在线"), OFF_LINE(2, "离线");

    private final Integer type;
    private final String info;

    OnlineStatusEnum(Integer type, String info) {
        this.type = type;
        this.info = info;
    }

    public Integer getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }
}
