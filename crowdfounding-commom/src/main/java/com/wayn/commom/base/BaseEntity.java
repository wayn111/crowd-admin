package com.wayn.commom.base;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity<T> implements Serializable {
    private static final long serialVersionUID = 6746161463502783836L;

    @TableField(exist = false)
    private String startTime;

    @TableField(exist = false)
    private String endTime;

    private Date createTime;

    public String getStartTime() {
        return startTime;
    }

    public BaseEntity setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public BaseEntity setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BaseEntity<T> setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
