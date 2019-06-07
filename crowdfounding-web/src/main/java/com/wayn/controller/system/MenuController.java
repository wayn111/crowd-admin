package com.wayn.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import com.wayn.domain.Menu;
import com.wayn.domain.vo.Tree;
import com.wayn.framework.util.ShiroUtil;
import com.wayn.mapper.RoleMenuDao;
import com.wayn.service.MenuService;

@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseControlller {
	@Autowired
	private MenuService menuService;

	private static final String PREFIX = "system/menu";

	@GetMapping
	public String menuIndex(Model model) {
		return PREFIX + "/menu";
	}

	@RequiresPermissions("sys:menu:menu")
	@ResponseBody
	@PostMapping("/list")
	public List<Menu> list(Model model) {
		List<Menu> list = new ArrayList<Menu>();
		list.addAll(menuService.selectList(new EntityWrapper<Menu>().orderBy("sort")));
		return list;
	}

	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pid}")
	public String add(Model model, @PathVariable("pid") Long pid) {
		model.addAttribute("pid", pid);
		if (pid == 0) {
			model.addAttribute("pName", "顶级节点");
		} else {
			model.addAttribute("pName", menuService.selectById(pid).getMenuName());
		}
		return PREFIX + "/add";
	}

	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Menu menu = menuService.selectById(id);
		model.addAttribute("menu", menu);
		model.addAttribute("pid", menu.getPid());
		String pName = "";
		if (menu.getPid() == 0) {
			pName = "顶级节点";
		} else {
			pName = menuService.selectById(menu.getPid()).getMenuName();
		}
		model.addAttribute("pName", pName);
		return PREFIX + "/edit";
	}

	@GetMapping("/chooseIcon")
	public String chooseIcon(Model model) {
		return PREFIX + "/icon";
	}

	@RequiresPermissions("sys:menu:add")
	@ResponseBody
	@PostMapping("/addSave")
	public Response addSave(Model model, Menu menu) {
		menuService.insert(menu);
		ShiroUtil.clearCachedAuthorizationInfo();
		return Response.success("菜单新增成功");
	}

	@RequiresPermissions("sys:menu:edit")
	@ResponseBody
	@PostMapping("/editSave")
	public Response editSave(Model model, Menu menu) {
		menuService.updateById(menu);
		ShiroUtil.clearCachedAuthorizationInfo();
		return Response.success("菜单修改成功");
	}

	@RequiresPermissions("sys:menu:remove")
	@ResponseBody
	@DeleteMapping("/remove/{id}")
	public Response remove(Model model, @PathVariable("id") Long id) {
		menuService.deleteById(id);
		ShiroUtil.clearCachedAuthorizationInfo();
		return Response.success("菜单删除成功");
	}

	@ResponseBody
	@PostMapping("/tree")
	public Tree<Menu> tree(Model model) {
		return menuService.getTree();
	}

	@GetMapping("/treeView")
	public String treeView(Model model) {
		return PREFIX + "/treeView";
	}

	@ResponseBody
	@PostMapping("/tree/{roleId}")
	Tree<Menu> tree(@PathVariable("roleId") String roleId) {
		Tree<Menu> tree = menuService.getTree(roleId);
		return tree;
	}
}
