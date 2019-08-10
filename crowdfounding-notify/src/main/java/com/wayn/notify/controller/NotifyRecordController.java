package com.wayn.notify.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.service.NotifyRecordService;
import com.wayn.commom.util.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/oa/notifyRecord")
@Controller
public class NotifyRecordController extends BaseControlller {

    private static final String PREFIX = "wayn/notifyRecord";

    @Autowired
    private NotifyRecordService notifyRecordService;

    @RequiresPermissions("wayn:notifyRecord:list")
    @GetMapping
    public String NotifyRecordIndex() {
        return PREFIX + "/type";
    }

    @RequiresPermissions("wayn:notifyRecord:list")
    @ResponseBody
    @PostMapping("/list")
    public Page<NotifyRecord> list(Model model, NotifyRecord notifyRecord) {
        Page<NotifyRecord> page = getPage();
        return notifyRecordService.selectNotifyRecordList(page, notifyRecord);
    }

    @RequiresPermissions("wayn:notifyRecord:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return PREFIX + "/add";
    }

    @RequiresPermissions("wayn:notifyRecord:edit")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        NotifyRecord notifyRecord = notifyRecordService.selectById(id);
        modelMap.put("notifyRecord", notifyRecord);
        return PREFIX + "/edit";
    }

    @RequiresPermissions("wayn:notifyRecord:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, NotifyRecord notifyRecord) {
        notifyRecordService.save(notifyRecord);
        return Response.success("新增成功");
    }

    @RequiresPermissions("wayn:notifyRecord:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, NotifyRecord notifyRecord) {
        notifyRecordService.update(notifyRecord);
        return Response.success("修改成功");
    }


    @RequiresPermissions("wayn:notifyRecord:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) {
        notifyRecordService.remove(id);
        return Response.success("删除成功");
    }

    @RequiresPermissions("wayn:notifyRecord:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) {
        notifyRecordService.batchRemove(ids);
        return Response.success("删除成功");
    }

}
