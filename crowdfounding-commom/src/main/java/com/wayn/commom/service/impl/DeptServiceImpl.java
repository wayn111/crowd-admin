package com.wayn.commom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.commom.dao.DeptDao;
import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.vo.Tree;
import com.wayn.commom.service.DeptService;
import com.wayn.commom.util.TreeBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @CacheEvict(value = "deptCache", allEntries = true)
    @Override
    public boolean saveDept(Dept dept) {
        dept.setCreateTime(new Date());
        return save(dept);
    }

    @CacheEvict(value = "deptCache", allEntries = true)
    @Override
    public boolean update(Dept dept) {
        return updateById(dept);
    }

    @CacheEvict(value = "deptCache", allEntries = true)
    @Override
    public boolean remove(Long id) {
        return removeById(id);
    }

    @Cacheable(value = "deptCache", key = "#root.method  + '_dept'")
    @Override
    public Tree<Dept> getTree() {
        List<Tree<Dept>> trees = new ArrayList<>();
        List<Dept> depts = deptDao.selectList(new QueryWrapper<>());
        List<Long> subDeptIds = new ArrayList<>();
        for (Dept dept : depts) {
            boolean flag = true;
            for (Dept subDept : depts) {
                if (dept.getId().equals(subDept.getPid())) {
                    flag = false;
                    break;
                }
            }
            if (flag) subDeptIds.add(dept.getId());
        }
        depts.forEach(dept -> {
            Tree<Dept> tree = new Tree<>();
            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getPid().toString());
            tree.setText(dept.getDeptName());
            tree.setType("dir");
            if (subDeptIds.contains(dept.getId())) {
                tree.setType("file");
            }
            trees.add(tree);
        });
        return TreeBuilderUtil.build(trees);
    }

    @Override
    public List<Long> listChildrenIds(Long pid) {
        List<Dept> list = deptDao.selectList(new QueryWrapper<>());
        return treeDept(list, pid);
    }

    public List<Long> treeDept(List<Dept> list, Long pid) {
        List<Long> deptIds = new ArrayList<>();
        deptIds.add(pid);
        list.forEach(dept -> {
            if (pid.equals(dept.getPid())) {
                deptIds.addAll(treeDept(list, dept.getId()));
                deptIds.add(dept.getId());
            }
        });
        return deptIds;
    }

    @Cacheable(value = "deptCache", key = "#root.method  + '_' + #root.args[0]")
    @Override
    public List<Dept> list(Dept dept) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.like("deptName", dept.getDeptName());
        return list(wrapper.orderByAsc("sort"));
    }

}
