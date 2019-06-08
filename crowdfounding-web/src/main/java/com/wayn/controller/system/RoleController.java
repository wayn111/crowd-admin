package com.wayn.controller.system;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.exception.BusinessException;
import com.wayn.commom.util.Response;
import com.wayn.domain.Role;
import com.wayn.domain.User;
import com.wayn.service.MenuService;
import com.wayn.service.RoleService;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseControlller {
	private static final String PREFIX = "system/role";
	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@GetMapping
	public String roleIndex(Model model) {
		return PREFIX + "/role";
	}

	@RequiresPermissions("sys:role:role")
	@ResponseBody
	@PostMapping("/list")
	public Page<Role> list(Model model, @RequestBody Map<String, Object> params) {
		Page<Role> page = getPage(params);
		return roleService.listPage(page, params);
	}

	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	public String add(Model model) {
		return PREFIX + "/add";
	}

	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit")
	public String edit(Model model, String id) {
		Role role = roleService.selectById(id);
		model.addAttribute("role1", role);
		return PREFIX + "/edit";
	}

	@RequiresPermissions("sys:role:add")
	@ResponseBody
	@PostMapping("/addSave")
	public Response addSave(Model model, Role role, String menuIds) {
		role.setCreateTime(new Date());
		roleService.save(role, menuIds);
		return Response.success("新增角色成功");
	}

	@RequiresPermissions("sys:role:edit")
	@ResponseBody
	@PostMapping("/editSave")
	public Response editSave(Model model, Role role, String menuIds) {
		roleService.update(role, menuIds);
		return Response.success("修改角色成功");
	}

	@RequiresPermissions("sys:role:remove")
	@ResponseBody
	@DeleteMapping("/remove/{roleId}")
	public Response remove(Model model, @PathVariable("roleId") String roleId) throws BusinessException {
		roleService.remove(roleId);
		return Response.success("删除角色成功");
	}

	@RequiresPermissions("sys:role:remove")
	@ResponseBody
	@PostMapping("/batchRemove")
	public Response batchRemove(Model model, @RequestParam("ids[]") String[] ids) throws BusinessException {
		roleService.batchRemove(ids);
		return Response.success("批量删除角色成功");
	}
}
