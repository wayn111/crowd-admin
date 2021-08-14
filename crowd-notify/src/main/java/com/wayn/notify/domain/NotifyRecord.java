package com.wayn.notify.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayn.common.base.BaseEntity;

import java.util.Date;

/**
 * 通知记录表 oa_notify_record
 *
 * @author wayn
 * @date 2019-08-10
 */
@TableName("oa_notify_record")
public class NotifyRecord extends BaseEntity {

    private static final long serialVersionUID = -2348311485953647689L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 关联通知id
     */
    private Long notifyId;
    /**
     * 接收人id
     */
    private String receiveUserId;
    /**
     * 接收人name
     */
    private String receiveUserName;
    /**
     * 是否已读
     */
    private Boolean isRead;
    /**
     * 阅读时间
     */
    private Date readTime;

    public Long getId() {
        return id;
    }

    public NotifyRecord setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getNotifyId() {
        return notifyId;
    }

    public NotifyRecord setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
        return this;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public NotifyRecord setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
        return this;
    }

    public Boolean setIsRead() {
        return isRead;
    }

    public Boolean getRead() {
        return isRead;
    }

    public NotifyRecord setRead(Boolean read) {
        isRead = read;
        return this;
    }

    public Date getReadTime() {
        return readTime;
    }

    public NotifyRecord setReadTime(Date readTime) {
        this.readTime = readTime;
        return this;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public NotifyRecord setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
        return this;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    @Override
    public String toString() {
        return "NotifyRecord{" +
                "id=" + id +
                ", notifyId=" + notifyId +
                ", receiveUserId='" + receiveUserId + '\'' +
                ", receiveUserName='" + receiveUserName + '\'' +
                ", isRead=" + isRead +
                ", readTime=" + readTime +
                "} " + super.toString();
    }
}
