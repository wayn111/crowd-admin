package com.wayn.controller.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
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
import com.wayn.commom.util.Response;
import com.wayn.domain.Role;
import com.wayn.domain.User;
import com.wayn.domain.vo.RoleChecked;
import com.wayn.service.DeptService;
import com.wayn.service.RoleService;
import com.wayn.service.UserRoleService;
import com.wayn.service.UserService;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseControlller {
	private static final String PREFIX = "system/user";

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private UserRoleService userRoleService;

	@GetMapping
	public String userIndex(Model model) {
		return PREFIX + "/user";
	}

	@RequiresPermissions("sys:user:user")
	@ResponseBody
	@PostMapping("/list")
	public Page<User> list(Model model, @RequestBody Map<String, Object> params) {
		Page<User> page = getPage(params);
		return userService.listPage(page, params);
	}

	@RequiresPermissions("sys:user:add")
	@GetMapping("/add")
	public String add(Model model) {
		List<Role> list = roleService.selectList(new EntityWrapper<Role>());
		model.addAttribute("roles", list);
		return PREFIX + "/add";
	}

	@RequiresPermissions("sys:user:edit")
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		User user = userService.selectById(id);
		model.addAttribute("user", user);
		String deptName = deptService.selectById(user.getDeptId()).getDeptName();
		model.addAttribute("deptName", deptName);
		List<RoleChecked> roleCheckedList = roleService.listCheckedRolesByUid(id);
		model.addAttribute("roles", roleCheckedList);
		return PREFIX + "/edit";
	}

	@RequiresPermissions("sys:user:resetPwd")
	@GetMapping("/resetPwd/{id}")
	public String resetPwd(Model model, @PathVariable("id") String id) {
		model.addAttribute("id", id);
		return PREFIX + "/resetPwd";
	}

	@RequiresPermissions("sys:user:resetPwd")
	@ResponseBody
	@PostMapping("/resetPwd")
	public Response resetPwd(Model model, @RequestParam String id, @RequestParam String password) {
		userService.resetPwd(id, password);
		return Response.success("修改用户密码成功");
	}

	@ResponseBody
	@PostMapping("/addExit")
	public boolean addExit(Model model, @RequestParam Map<String, Object> params) {
		return !userService.exit(params);
	}

	@ResponseBody
	@PostMapping("/editExit")
	public boolean editExit(Model model, @RequestParam Map<String, Object> params) {
		return !userService.exit(params);
	}
	
	@RequiresPermissions("sys:user:add")
	@ResponseBody
	@PostMapping("/addSave")
	public Response addSave(Model model, User user, String roleIds) {
		user.setCreateTime(new Date());
		user.setPassword(new SimpleHash("MD5", user.getPassword(), user.getUserName(), 1024).toString());
		userService.save(user, roleIds);
		return Response.success("新增用户成功");
	}

	@RequiresPermissions("sys:user:edit")
	@ResponseBody
	@PostMapping("/editSave")
	public Response editSave(Model model, User user, String roleIds) {
		userService.update(user, roleIds);
		return Response.success("修改用户成功");

	}

	@RequiresPermissions("sys:user:remove")
	@ResponseBody
	@DeleteMapping("/remove/{id}")
	public Response remove(Model model, @PathVariable("id") String id) {
		userService.remove(id);
		return Response.success("删除用户成功");

	}

	@RequiresPermissions("sys:user:remove")
	@ResponseBody
	@PostMapping("/batchRemove")
	public Response batchRemove(Model model, @RequestParam("ids[]") String[] ids) {
		userService.batchRemove(ids);
		return Response.success("删除用户成功");

	}
}
