package com.wayn.commom.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity<T> implements Serializable {
    private static final long serialVersionUID = 6746161463502783836L;

    @TableField(exist = false)
    private String startTime;

    @TableField(exist = false)
    private String endTime;

    private String remarks;

    private Date createTime;

    @TableField(exist = false)
    private Date updateTime;

    @TableField(exist = false)
    private EntityWrapper<T> wrapper;

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

    public EntityWrapper<T> getWrapper() {
        return wrapper;
    }

    public BaseEntity<T> setWrapper(EntityWrapper<T> wrapper) {
        this.wrapper = wrapper;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BaseEntity<T> setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public BaseEntity<T> setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BaseEntity<T> setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
