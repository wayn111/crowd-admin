package com.wayn.web.controller.system;

import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.vo.Tree;
import com.wayn.commom.enums.Operator;
import com.wayn.commom.service.DeptService;
import com.wayn.commom.util.Response;
import com.wayn.framework.annotation.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseControlller {
    private static final String PREFIX = "system/dept";

    @Autowired
    private DeptService deptService;

    @RequiresPermissions("sys:dept:dept")
    @GetMapping
    public String deptIndex() {
        return PREFIX + "/dept";
    }

    @Log(value = "部门管理")
    @RequiresPermissions("sys:dept:dept")
    @ResponseBody
    @PostMapping("/list")
    public List<Dept> list(Model model, Dept dept) {
        return deptService.list(dept);
    }

    @RequiresPermissions("sys:dept:add")
    @GetMapping("/add/{pid}")
    public String add(Model model, @PathVariable("pid") Long pid) {
        model.addAttribute("pid", pid);
        if (pid == 0) {
            model.addAttribute("pName", "顶级节点");
        } else {
            model.addAttribute("pName", deptService.selectById(pid).getDeptName());
        }
        return PREFIX + "/add";
    }

    @RequiresPermissions("sys:dept:edit")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Dept dept = deptService.selectById(id);
        Long pid = dept.getPid();
        model.addAttribute("pid", pid);
        model.addAttribute("dept", dept);
        if (pid == 0) {
            model.addAttribute("pName", "顶级节点");
        } else {
            model.addAttribute("pName", deptService.selectById(pid).getDeptName());
        }
        return PREFIX + "/edit";
    }

    @Log(value = "部门管理", operator = Operator.ADD)
    @RequiresPermissions("sys:dept:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(Model model, Dept dept) {
        deptService.save(dept);
        return Response.success("部门新增成功");
    }

    @Log(value = "部门管理", operator = Operator.UPDATE)
    @RequiresPermissions("sys:dept:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(Model model, Dept dept) {
        deptService.update(dept);
        return Response.success("部门修改成功");
    }

    @Log(value = "部门管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:dept:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(Model model, @PathVariable("id") Long id) {
        deptService.remove(id);
        return Response.success("部门删除成功");
    }

    @ResponseBody
    @PostMapping("/tree")
    public Tree<Dept> tree(Model model) {
        return deptService.getTree();
    }

    @GetMapping("/treeView")
    public String treeView(Model model) {
        return PREFIX + "/treeView";
    }
}
