package com.wayn.commom.util;

import com.wayn.commom.domain.vo.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ztree所需json数据返回帮助类
 * @author wayn
 *
 */
public class TreeBuilderUtil {

	/**
	 * 按照root节点为id为0开时构建
	 * @param <T>
	 * @param nodes List<Tree<T>> list数据集合
	 * @return
	 */
	public static <T> Tree<T> build(List<Tree<T>> nodes) {

		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

		for (Tree<T> children : nodes) {
			
			
			String pid = children.getParentId();
			if (pid == null || "0".equals(pid)) {
				topNodes.add(children);
				continue;
			}

			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					continue;
				}
			}

		}

		Tree<T> root = new Tree<T>();
		if (topNodes.size() == 1) {
			root = topNodes.get(0);
		} else {
			root.setId("-1");
			root.setParentId("");
			root.setHasParent(false);
			root.setChildren(true);
			root.setChecked(true);
			root.setChildren(topNodes);
			root.setText("顶级节点");
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			root.setState(state);
		}

		return root;
	}

	/**
	 * 按照root节点为id为idParam开时构建
	 * @param <T>
	 * @param nodes List<Tree<T>> list数据集合
	 * @param idParam root节点id
	 * @return
	 */
	public static <T> List<Tree<T>> build(List<Tree<T>> nodes, String idParam) {
		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

		for (Tree<T> children : nodes) {

			String pid = children.getParentId();
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(children);

				continue;
			}

			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);

					continue;
				}
			}

		}
		return topNodes;
	}
}
