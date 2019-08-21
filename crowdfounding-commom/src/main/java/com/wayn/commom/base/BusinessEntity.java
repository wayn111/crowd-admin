package com.wayn.commom.base;

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
        return "BusinessEntity{" +
                "remarks='" + remarks + '\'' +
                "} " + super.toString();
    }
}
