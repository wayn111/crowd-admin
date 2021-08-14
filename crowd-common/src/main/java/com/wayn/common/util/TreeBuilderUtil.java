package com.wayn.common.util;

import com.wayn.common.domain.vo.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ztree所需json数据返回帮助类
 *
 * @author wayn
 */
public class TreeBuilderUtil {

    /**
     * 按照root节点为id为0开时构建
     *
     * @param <T>
     * @param nodes List<Tree<T>> list数据集合
     * @return
     */
    public static <T> Tree<T> build(List<Tree<T>> nodes) {

        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();

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
                }
            }

        }

        Tree<T> root = new Tree<>();
        if (topNodes.size() == 1) {
            root = topNodes.get(0);
        } else {
            root.setId("-1");
            root.setParentId("");
            root.setType("root");
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

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.setId("1");
        tree.setParentId("0");
        tree.setText("一级");
        Tree tree1 = new Tree();
		tree1.setId("2");
		tree1.setParentId("1");
		tree1.setText("2级");
        Tree tree2 = new Tree();
		tree2.setId("3");
		tree2.setParentId("2");
		tree2.setText("3级");
		List<Tree<Object>> objects = new ArrayList<>();
		objects.add(tree);
		objects.add(tree1);
		objects.add(tree2);
		System.out.println(build(objects));
	}
}
