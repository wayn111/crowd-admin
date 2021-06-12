package com.wayn.commom.base;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.StringJoiner;

public class BusinessEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 120550327270507569L;

    @Excel(name = "备注", width = 20)
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public BusinessEntity<T> setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BusinessEntity.class.getSimpleName() + "[", "]")
                .add("remarks='" + remarks + "'")
                .toString();
    }
}
