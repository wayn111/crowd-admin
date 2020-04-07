package com.wayn.commom.base;

import java.util.StringJoiner;

public class BusinessEntity<T> extends BaseEntity<T> {

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
