package com.wayn.service;

import com.wayn.domain.Dept;
import com.wayn.domain.vo.Tree;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface DeptService extends IService<Dept> {

	Tree<Dept> getTree();

	List<Long> listChildrenIds(Long pid);
}
