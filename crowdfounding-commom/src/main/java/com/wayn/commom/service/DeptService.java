package com.wayn.commom.service;

import com.baomidou.mybatisplus.service.IService;
import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.vo.Tree;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface DeptService extends IService<Dept> {

	boolean save(Dept dept);

	boolean update(Dept dept);

	boolean remove(Integer id);

	Tree<Dept> getTree();

	List<Long> listChildrenIds(Long pid);
	
	List<Dept> list(Dept dept);
}
