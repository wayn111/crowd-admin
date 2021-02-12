package com.wayn.notify.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * 通知记录表实体VO对象
 *
 * @author wayn
 * @date 2019-08-10
 */
public class NotifyRecordVO extends NotifyVO {

    private static final long serialVersionUID = 4336896848276179808L;
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
    @Excel(name = "是否已读", replace = {"已读_true", "未读_false"})
    private Boolean isRead;
    /**
     * 阅读时间
     */
    private Date readTime;

    public Long getId() {
        return id;
    }

    public NotifyRecordVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getNotifyId() {
        return notifyId;
    }

    public NotifyRecordVO setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
        return this;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public NotifyRecordVO setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
        return this;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public NotifyRecordVO setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
        return this;
    }

    public Boolean getRead() {
        return isRead;
    }

    public NotifyRecordVO setRead(Boolean read) {
        isRead = read;
        return this;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public NotifyRecordVO setIsRead(Boolean read) {
        isRead = read;
        return this;
    }

    public Date getReadTime() {
        return readTime;
    }

    public NotifyRecordVO setReadTime(Date readTime) {
        this.readTime = readTime;
        return this;
    }

    @Override
    public String toString() {
        return "NotifyRecordVO{" +
                "id=" + id +
                ", notifyId=" + notifyId +
                ", receiveUserId='" + receiveUserId + '\'' +
                ", receiveUserName='" + receiveUserName + '\'' +
                ", isRead=" + isRead +
                ", readTime=" + readTime +
                "} " + super.toString();
    }
}
