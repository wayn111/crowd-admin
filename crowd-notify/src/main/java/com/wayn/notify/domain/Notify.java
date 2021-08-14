package com.wayn.notify.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayn.common.base.BusinessEntity;

import java.util.Date;

/**
 * 通知通告表 oa_notify
 *
 * @author wayn
 * @date 2019-08-10
 */
@TableName("oa_notify")
public class Notify extends BusinessEntity {

    private static final long serialVersionUID = 671279142887585179L;

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
     *  通知状态 1 已发布 -1 未发布
     */
    private Integer notifyState;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 页面查询时间
     */
    @TableField(exist = false)
    private String publishStartTime;
    @TableField(exist = false)
    private String publishEndTime;

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
    private Date updateTime;
    /**
     * 删除标记 0 存在 1 删除
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public Integer getNotifyState() {
        return notifyState;
    }

    public void setNotifyState(Integer notifyState) {
        this.notifyState = notifyState;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Notify setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public Notify setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Notify setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Notify setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public Notify setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
        return this;
    }

    public String getPublishStartTime() {
        return publishStartTime;
    }

    public Notify setPublishStartTime(String publishStartTime) {
        this.publishStartTime = publishStartTime;
        return this;
    }

    public String getPublishEndTime() {
        return publishEndTime;
    }

    public Notify setPublishEndTime(String publishEndTime) {
        this.publishEndTime = publishEndTime;
        return this;
    }


    @Override
    public String toString() {
        return "Notify{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", files='" + files + '\'' +
                ", notifyState=" + notifyState +
                ", publishTime=" + publishTime +
                ", publishStartTime='" + publishStartTime + '\'' +
                ", publishEndTime='" + publishEndTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                "} " + super.toString();
    }
}
