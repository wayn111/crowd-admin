package com.wayn.common.enums;

public enum StateEnum {
    ENABLE(1), // 启用
    DISABLE(-1); // 禁用

    private Integer state;

    StateEnum(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public StateEnum setState(Integer state) {
        this.state = state;
        return this;
    }
}
