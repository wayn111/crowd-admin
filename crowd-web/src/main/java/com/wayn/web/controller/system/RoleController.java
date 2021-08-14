package com.wayn.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.annotation.Log;
import com.wayn.common.base.BaseController;
import com.wayn.common.domain.Role;
import com.wayn.common.enums.Operator;
import com.wayn.common.exception.BusinessException;
import com.wayn.common.service.DictService;
import com.wayn.common.service.RoleService;
import com.wayn.common.util.ParameterUtil;
import com.wayn.common.util.Response;
import com.wayn.framework.util.ShiroCacheUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    private static final String PREFIX = "system/role";
    @Autowired
    private RoleService roleService;

    @Autowired
    private DictService dictService;

    @RequiresPermissions("sys:role:role")
    @GetMapping
    public String roleIndex(Model model) {
        model.addAttribute("states", dictService.selectDictsValueByType("state"));
        return PREFIX + "/role";
    }

    @Log(value = "角色管理")
    @RequiresPermissions("sys:role:role")
    @ResponseBody
    @PostMapping("/list")
    public Page<Role> list(Model model, Role role) {
        Page<Role> page = getPage();
        //设置通用查询字段
        ParameterUtil.setWrapper();
        return roleService.listPage(page, role);
    }

    @RequiresPermissions("sys:role:add")
    @GetMapping("/add")
    public String add(Model model) {
        return PREFIX + "/add";
    }

    @RequiresPermissions("sys:role:edit")
    @GetMapping("/edit")
    public String edit(Model model, String id) {
        Role role = roleService.getById(id);
        model.addAttribute("role1", role);
        return PREFIX + "/edit";
    }

    @Log(value = "角色管理", operator = Operator.ADD)
    @RequiresPermissions("sys:role:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(Model model, Role role, String menuIds) {
        role.setCreateTime(new Date());
        boolean save = roleService.save(role, menuIds);
        if (!save) {
            return Response.error("新增角色失败");
        }
        ShiroCacheUtil.clearCachedAuthorizationInfo();
        return Response.success("新增角色成功");
    }

    @Log(value = "角色管理", operator = Operator.UPDATE)
    @RequiresPermissions("sys:role:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(Model model, Role role, String menuIds) throws Exception {
        boolean update = roleService.update(role, menuIds);
        if (!update) {
            return Response.error("修改角色失败");
        }
        ShiroCacheUtil.clearCachedAuthorizationInfo();
        return Response.success("修改角色成功");
    }

    @Log(value = "角色管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:role:remove")
    @ResponseBody
    @DeleteMapping("/remove/{roleId}")
    public Response remove(Model model, @PathVariable("roleId") String roleId) throws BusinessException {
        return Response.result(roleService.remove(roleId), "删除角色成功");
    }

    @Log(value = "角色管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:role:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(Model model, @RequestParam("ids[]") String[] ids) throws BusinessException {
        return Response.result(roleService.batchRemove(ids), "批量删除角色成功");
    }

    @PostMapping("/export")
    public void export(Role role, HttpServletResponse response) throws IOException {
        ParameterUtil.setWrapper();
        roleService.export(role, response, request);
    }
}
