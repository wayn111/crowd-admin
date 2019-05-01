package com.wayn.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@TableName("sys_menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 父级菜单ID
	 */
	private Long pid;

	/**
	 * 连接地址
	 */
	private String url;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 类别，1表示目录，2表示菜单，3表示按钮
	 */
	private Integer type;

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 资源名称（菜单对应权限）
	 */
	private String resource;

	@TableField(exist = false)
	private List<Menu> children = new ArrayList<Menu>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu{" + "id=" + id + ", menuName=" + menuName + ", pid=" + pid + ", url=" + url + ", icon=" + icon
				+ ", sort=" + sort + ", type=" + type + ", code=" + code + ", resource=" + resource + "}";
	}
}
