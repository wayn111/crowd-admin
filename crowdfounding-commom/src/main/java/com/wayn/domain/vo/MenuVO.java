package com.wayn.domain.vo;

import com.wayn.commom.base.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public class MenuVO extends BaseEntity<MenuVO> {

	/**
	 * 主键
	 */
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
	private BigDecimal sort;

	/**
	 * 类别，1表示目录，2表示菜单，3表示按钮
	 */
	private String type;

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 资源名称（菜单对应权限）
	 */
	private String resource;

	private List<MenuVO> children = new ArrayList<MenuVO>();

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

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public MenuVO setType(String type) {
		this.type = type;
		return this;
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

	public List<MenuVO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuVO> children) {
		this.children = children;
	}

}
