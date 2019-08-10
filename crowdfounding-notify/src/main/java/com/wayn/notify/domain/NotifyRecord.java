package com.wayn.notify.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wayn.commom.base.BaseEntity;

import java.util.Date;

/**
 * 通知记录表 oa_notify_record
 *
 * @author wayn
 * @date 2019-08-10
 */
@TableName("oa_notify_record")
public class NotifyRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

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
     * 是否已读
     */
    private boolean isRead;
    /**
     * 阅读时间
     */
    private Date readDate;

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

    public boolean isRead() {
        return isRead;
    }

    public NotifyRecord setRead(boolean read) {
        isRead = read;
        return this;
    }

    public Date getReadDate() {
        return readDate;
    }

    public NotifyRecord setReadDate(Date readDate) {
        this.readDate = readDate;
        return this;
    }

    @Override
    public String toString() {
        return "NotifyRecord{" +
                "id=" + id +
                ", notifyId=" + notifyId +
                ", receiveUserId='" + receiveUserId + '\'' +
                ", isRead=" + isRead +
                ", readDate=" + readDate +
                "} " + super.toString();
    }
}
