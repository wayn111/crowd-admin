package com.wayn.quartz.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayn.common.base.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 定时任务调度日志表 sys_job_log
 *
 * @author wayn
 * @date 2019-09-04
 */
@TableName("sys_job_log")
public class JobLog extends BaseEntity {

    private static final long serialVersionUID = -5352824197215057392L;

    /**
     * 任务日志ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    private String jobName;
    /**
     * 任务组名
     */
    @Excel(name = "任务组名")
    private String jobGroup;
    /**
     * 调用目标字符串
     */
    @Excel(name = "调用目标字符串", width = 40)
    private String invokeTarget;
    /**
     * 日志信息
     */
    @Excel(name = "日志信息", width = 40)
    private String jobMessage;
    /**
     * 执行状态（1正常 -1失败）
     */
    @Excel(name = "执行状态", type = 10, replace = {"正常_1", "失败_-1"})
    private Integer jobState;
    /**
     * 异常信息
     */
    private String exceptionInfo;

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

    public String getJobMessage() {
        return jobMessage;
    }

    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage;
    }

    public Integer getJobState() {
        return jobState;
    }

    public JobLog setJobState(Integer jobState) {
        this.jobState = jobState;
        return this;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("jobName", getJobName())
                .append("jobGroup", getJobGroup())
                .append("invokeTarget", getInvokeTarget())
                .append("jobMessage", getJobMessage())
                .append("status", getJobState())
                .append("exceptionInfo", getExceptionInfo())
                .append("createTime", getCreateTime())
                .append(super.toString()).toString();
    }
}
