package com.wayn.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.common.domain.Dept;
import com.wayn.common.domain.vo.Tree;

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

	boolean saveDept(Dept dept);

	boolean update(Dept dept);

	boolean remove(Long id);

	Tree<Dept> getTree();

	List<Long> listChildrenIds(Long pid);

	List<Dept> list(Dept dept);
}
