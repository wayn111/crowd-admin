package com.wayn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.util.TreeBuilderUtil;
import com.wayn.domain.Dept;
import com.wayn.domain.Menu;
import com.wayn.domain.vo.Tree;
import com.wayn.mapper.DeptDao;
import com.wayn.service.DeptService;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

	@Autowired
	private DeptDao deptDao;

	@Override
	public Tree<Dept> getTree() {
		List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
		List<Dept> menus = deptDao.selectList(new EntityWrapper<Dept>());
		menus.forEach(menu -> {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(menu.getId().toString());
			tree.setParentId(menu.getPid().toString());
			tree.setText(menu.getDeptName());
			trees.add(tree);
		});
		return TreeBuilderUtil.build(trees);
	}

	@Override
	public List<Long> listChildrenIds(Long pid) {
		List<Dept> list = deptDao.selectList(new EntityWrapper<Dept>());
		return treeDept(list, pid);
	}

	public List<Long> treeDept(List<Dept> list, Long pid) {
		List<Long> deptIds = new ArrayList<Long>();
		deptIds.add(pid);
		list.forEach(dept -> {
			if (pid == dept.getPid()) {
				deptIds.addAll(treeDept(list, dept.getId()));
				deptIds.add(dept.getId());
			}
		});
		return deptIds;
	}

	@Override
	public List<Dept> list(Map<String, Object> params) {
		String deptName = (String) params.get("deptName");
		EntityWrapper<Dept> wrapper = new EntityWrapper<Dept>();
		wrapper.like("deptName", deptName);
		return selectList(wrapper.orderBy("sort"));
	}

}
