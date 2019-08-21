package com.wayn.notify.domain.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wayn.commom.base.BaseEntity;

/**
 * 通知通告实体VO对象
 */
public class NotifyVO extends BaseEntity<NotifyVO> {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 通知类型 1 公告 2 通知
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 附件
     */
    private String files;
    /**
     * 通知状态 1 已发布 -1 未发布
     */
    private Integer notifyState;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 删除标记
     */
    private String delFlag;

    private String remarks;

    public Long getId() {
        return id;
    }

    public NotifyVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public NotifyVO setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NotifyVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NotifyVO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getFiles() {
        return files;
    }

    public NotifyVO setFiles(String files) {
        this.files = files;
        return this;
    }

    public Integer getNotifyState() {
        return notifyState;
    }

    public NotifyVO setNotifyState(Integer notifyState) {
        this.notifyState = notifyState;
        return this;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public NotifyVO setPublishTime(String publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public NotifyVO setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public NotifyVO setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public NotifyVO setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public NotifyVO setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public NotifyVO setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }
}
