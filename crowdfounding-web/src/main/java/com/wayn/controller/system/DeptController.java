package com.wayn.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.Response;
import com.wayn.domain.Dept;
import com.wayn.domain.vo.Tree;
import com.wayn.service.DeptService;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseControlller {
	private static final String PREFIX = "system/dept";

	@Autowired
	private DeptService deptService;

	@GetMapping
	public String deptIndex() {
		return PREFIX + "/dept";
	}

	@RequiresPermissions("sys:dept:dept")
	@ResponseBody
	@PostMapping("/list")
	public List<Dept> list(Model model) {
		List<Dept> list = new ArrayList<Dept>();
		list.addAll(deptService.selectList(new EntityWrapper<Dept>().orderBy("sort")));
		return list;
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

	@RequiresPermissions("sys:dept:add")
	@ResponseBody
	@PostMapping("/addSave")
	public Response addSave(Model model, Dept dept) {
		deptService.insert(dept);
		return Response.success("部门新增成功");
	}

	@RequiresPermissions("sys:dept:edit")
	@ResponseBody
	@PostMapping("/editSave")
	public Response editSave(Model model, Dept dept) {
		deptService.updateById(dept);
		return Response.success("部门修改成功");
	}

	@RequiresPermissions("sys:dept:remove")
	@ResponseBody
	@DeleteMapping("/remove/{id}")
	public Response remove(Model model, @PathVariable("id") Long id) {
		deptService.deleteById(id);
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
