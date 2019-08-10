package com.wayn.notify.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.Response;
import com.wayn.notify.domain.Notify;
import com.wayn.notify.service.NotifyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/oa/notify")
@Controller
public class NotifyController extends BaseControlller {

    private static final String PREFIX = "oa/notify";

    @Autowired
    private NotifyService notifyService;

    @RequiresPermissions("oa:notify:list")
    @GetMapping
    public String NotifyIndex() {
        return PREFIX + "/type";
    }

    @ResponseBody
    @GetMapping("/message")
    public Response message() {
        return Response.success().add("total", 1);
    }

    @RequiresPermissions("oa:notify:list")
    @ResponseBody
    @PostMapping("/list")
    public Page<Notify> list(Model model, Notify notify) {
        Page<Notify> page = getPage();
        return notifyService.selectNotifyList(page, notify);
    }

    @RequiresPermissions("oa:notify:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return PREFIX + "/add";
    }

    @RequiresPermissions("oa:notify:edit")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Notify notify = notifyService.selectById(id);
        modelMap.put("notify", notify);
        return PREFIX + "/edit";
    }

    @RequiresPermissions("oa:notify:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, Notify notify) {
        notifyService.save(notify);
        return Response.success("新增成功");
    }

    @RequiresPermissions("oa:notify:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, Notify notify) {
        notifyService.update(notify);
        return Response.success("修改成功");
    }


    @RequiresPermissions("oa:notify:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) {
        notifyService.remove(id);
        return Response.success("删除成功");
    }

    @RequiresPermissions("oa:notify:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) {
        notifyService.batchRemove(ids);
        return Response.success("删除成功");
    }

}
