package com.wayn.notify.domain;

/**
 * 消息通知提示对象
 */
public class NotifyRecordTip extends Notify {

    private static final long serialVersionUID = 8570042875494680049L;

    private Long notifyRecordId;

    private String before;

    public String getBefore() {
        return before;
    }

    public NotifyRecordTip setBefore(String before) {
        this.before = before;
        return this;
    }

    public Long getNotifyRecordId() {
        return notifyRecordId;
    }

    public NotifyRecordTip setNotifyRecordId(Long notifyRecordId) {
        this.notifyRecordId = notifyRecordId;
        return this;
    }

    @Override
    public String toString() {
        return "NotifyRecordTip{" +
                "notifyRecordId=" + notifyRecordId +
                ", before='" + before + '\'' +
                "} " + super.toString();
    }
}
