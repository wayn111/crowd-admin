package com.wayn.commom.domain.vo;

import java.util.Objects;
import java.util.StringJoiner;

public class EchartVO {

    private String name;

    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EchartVO echartVO = (EchartVO) o;
        return name.equals(echartVO.name) && value.equals(echartVO.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EchartVO.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("value=" + value)
                .toString();
    }
}
