package com.wayn.commom.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wayn.commom.base.BusinessEntity;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_dept")
public class Dept extends BusinessEntity<Dept> {

	private static final long serialVersionUID = 6236105927807085550L;

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

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Dept.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("pid=" + pid)
				.add("deptName='" + deptName + "'")
				.add("sort=" + sort)
				.toString();
	}
}
