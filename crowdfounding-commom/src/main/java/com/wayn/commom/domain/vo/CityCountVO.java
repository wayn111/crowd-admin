package com.wayn.commom.domain.vo;

import java.util.Objects;
import java.util.StringJoiner;

public class CityCountVO {

    private String loginLocation;

    private Long num;

    private String name;

    private Long value;

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityCountVO that = (CityCountVO) o;
        return Objects.equals(loginLocation, that.loginLocation) && Objects.equals(num, that.num) && Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginLocation, num, name, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CityCountVO.class.getSimpleName() + "[", "]")
                .add("loginLocation='" + loginLocation + "'")
                .add("num=" + num)
                .add("name='" + name + "'")
                .add("value='" + value + "'")
                .toString();
    }
}
