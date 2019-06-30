package com.wayn.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_dept")
public class Dept implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	private Long pid;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 描述
	 */
	private String deptDesc;

	/**
	 * 排序
	 */
	private BigDecimal sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", pid=" + pid + ", deptName=" + deptName + ", deptDesc=" + deptDesc + ", sort="
				+ sort + "]";
	}

}
