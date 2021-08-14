package com.wayn.common.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayn.common.base.BusinessEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 参数配置表 sys_config
 *
 * @author wayn
 * @date 2020-09-16
 */
@TableName("sys_config")
public class Config extends BusinessEntity {

    private static final long serialVersionUID = 8544099501857050777L;

    /**
     * 参数主键
     */
    @TableId(type = IdType.AUTO)
    private Integer configId;
    /**
     * 参数名称
     */
    @Excel(name = "参数名称", width = 30)
    private String configName;
    /**
     * 参数键名
     */
    @Excel(name = "参数键名", width = 20)
    private String configKey;
    /**
     * 参数键值
     */
    @Excel(name = "参数键值", width = 20)
    private String configValue;
    /**
     * 系统内置（Y是 N否）
     */
    @Excel(name = "系统内置", replace = {"是_Y", "否_-N"})
    private String configType;
    /**
     * 创建者
     */
    @Excel(name = "创建者", width = 20)
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigType() {
        return configType;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("configId", getConfigId())
                .append("configName", getConfigName())
                .append("configKey", getConfigKey())
                .append("configValue", getConfigValue())
                .append("configType", getConfigType())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remarks", getRemarks())
                .append(super.toString()).toString();
    }
}
