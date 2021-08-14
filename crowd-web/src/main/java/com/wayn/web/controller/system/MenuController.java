package com.wayn.web.controller.system;

import com.wayn.common.annotation.Log;
import com.wayn.common.base.BaseController;
import com.wayn.common.domain.Menu;
import com.wayn.common.domain.vo.MenuVO;
import com.wayn.common.domain.vo.Tree;
import com.wayn.common.enums.Operator;
import com.wayn.common.service.DictService;
import com.wayn.common.service.MenuService;
import com.wayn.common.util.Response;
import com.wayn.framework.util.ShiroCacheUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
    private static final String PREFIX = "system/menu";
    @Autowired
    private MenuService menuService;
    @Autowired
    private DictService dictService;

    @RequiresPermissions("sys:menu:menu")
    @GetMapping
    public String menuIndex(Model model) {
        model.addAttribute("menuTypes", dictService.selectDictsValueByType("menuType"));
        return PREFIX + "/menu";
    }

    @Log(value = "菜单管理")
    @RequiresPermissions("sys:menu:menu")
    @ResponseBody
    @PostMapping("/list")
    public List<Menu> list(Model model, MenuVO menu) {
        return menuService.list(menu);
    }

    @RequiresPermissions("sys:menu:add")
    @GetMapping("/add/{pid}")
    public String add(Model model, @PathVariable("pid") Long pid) {
        model.addAttribute("menuTypes", dictService.selectDictsValueByType("menuType"));
        model.addAttribute("pid", pid);
        if (pid == 0) {
            model.addAttribute("pName", "顶级节点");
        } else {
            model.addAttribute("pName", menuService.getById(pid).getMenuName());
        }
        return PREFIX + "/add";
    }

    @RequiresPermissions("sys:menu:edit")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Menu menu = menuService.getById(id);
        model.addAttribute("menuTypes", dictService.selectDictsValueByType("menuType"));
        model.addAttribute("menu", menu);
        model.addAttribute("pid", menu.getPid());
        String pName;
        if (menu.getPid() == 0) {
            pName = "顶级节点";
        } else {
            pName = menuService.getById(menu.getPid()).getMenuName();
        }
        model.addAttribute("pName", pName);
        return PREFIX + "/edit";
    }

    @GetMapping("/chooseIcon")
    public String chooseIcon(Model model) {
        return PREFIX + "/icon";
    }

    @Log(value = "菜单管理", operator = Operator.ADD)
    @RequiresPermissions("sys:menu:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(Model model, Menu menu) {
        menuService.save(menu);
        ShiroCacheUtil.clearCachedAuthorizationInfo();
        return Response.success("菜单新增成功");
    }

    @Log(value = "菜单管理", operator = Operator.UPDATE)
    @RequiresPermissions("sys:menu:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(Model model, Menu menu) {
        boolean update = menuService.update(menu);
        if (!update) {
            return Response.error("更新菜单失败");
        }
        ShiroCacheUtil.clearCachedAuthorizationInfo();
        return Response.success("菜单修改成功");
    }

    @Log(value = "菜单管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:menu:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(Model model, @PathVariable("id") Long id) {
        boolean remove = menuService.remove(id);
        if (!remove) {
            return Response.error("删除菜单失败");
        }
        ShiroCacheUtil.clearCachedAuthorizationInfo();
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
        return menuService.getTree(roleId);
    }
}
