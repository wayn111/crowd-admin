package com.wayn.commom.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 树形节点展示
 * @author wayn
 *
 * @param <T>
 */
public class Tree<T> implements Serializable {

	private static final long serialVersionUID = 1218560379804614405L;
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 显示节点文本
	 */
	private String text;
	/**
	 * 节点状态，open closed
	 */
	private Map<String, Object> state;
	/**
	 * 节点是否被选中 true false
	 */
	private Boolean checked = false;
	/**
	 * 节点属性
	 */
	private Map<String, Object> attributes;

	/**
	 * 节点的子节点
	 */
	private List<Tree<T>> children = new ArrayList<Tree<T>>();

	/**
	 * 父ID
	 */
	private String parentId;
	/**
	 * 是否有父节点
	 */
	private Boolean hasParent = false;
	/**
	 * 是否有子节点
	 */
	private Boolean hasChildren = false;

	/**
	 * 节点类型
	 */
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, Object> getState() {
		return state;
	}

	public void setState(Map<String, Object> state) {
		this.state = state;
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Tree<T>> children) {
		this.children = children;
	}

	public Boolean isHasParent() {
		return hasParent;
	}

	public void setHasParent(Boolean isParent) {
		this.hasParent = isParent;
	}

	public Boolean isHasChildren() {
		return hasChildren;
	}

	public void setChildren(Boolean isChildren) {
		this.hasChildren = isChildren;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Tree(String id, String text, Map<String, Object> state, Boolean checked, Map<String, Object> attributes,
				List<Tree<T>> children, Boolean isParent, Boolean isChildren, String parentID) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.hasParent = isParent;
		this.hasChildren = isChildren;
		this.parentId = parentID;
	}

	public Tree() {
		super();
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Tree.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("text='" + text + "'")
				.add("state=" + state)
				.add("checked=" + checked)
				.add("type=" + type)
				.add("attributes=" + attributes)
				.add("children=" + children)
				.add("parentId='" + parentId + "'")
				.add("hasParent=" + hasParent)
				.add("hasChildren=" + hasChildren)
				.toString();
	}
}
