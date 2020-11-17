package com.wayn.quartz.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wayn.commom.base.BusinessEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 定时任务调度表 sys_job
 *
 * @author wayn
 * @date 2019-09-04
 */
@TableName("sys_job")
public class Job extends BusinessEntity<Job> {

    private static final long serialVersionUID = 8774987738630789465L;

    /**
     * 任务ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务组名
     */
    private String jobGroup;
    /**
     * 调用目标字符串（1，springBean调用 2，通过Class.forName反射调用）
     */
    private String invokeTarget;
    /**
     * cron执行表达式
     */
    private String cronExpression;
    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
     */
    private String misfirePolicy;
    /**
     * 是否并发执行（1允许 -1禁止）
     */
    private Integer concurrent;
    /**
     * 状态（1正常 -1暂停）
     */
    private Integer jobState;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getInvokeTarget() {
        return invokeTarget;
    }

    public void setInvokeTarget(String invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    public Integer getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(Integer concurrent) {
        this.concurrent = concurrent;
    }

    public Integer getJobState() {
        return jobState;
    }

    public Job setJobState(Integer jobState) {
        this.jobState = jobState;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Job setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Job setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Job setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("jobName", jobName)
                .append("jobGroup", jobGroup)
                .append("invokeTarget", invokeTarget)
                .append("cronExpression", cronExpression)
                .append("misfirePolicy", misfirePolicy)
                .append("concurrent", concurrent)
                .append("jobState", jobState)
                .append("createBy", createBy)
                .append("createTime", super.getCreateTime())
                .append("updateBy", updateBy)
                .append("updateTime", updateTime)
                .append("remarks", super.getRemarks())
                .toString();
    }
}
