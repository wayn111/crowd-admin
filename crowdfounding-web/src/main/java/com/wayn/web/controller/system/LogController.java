package com.wayn.web.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.Response;
import com.wayn.commom.domain.Log;
import com.wayn.commom.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/system/log")
public class LogController extends BaseControlller {
    private static final String PREFIX = "system/log";

    @Autowired
    private LogService logService;

    @RequiresPermissions("sys:log:log")
    @GetMapping
    public String login(ModelMap map) {
        return PREFIX + "/log";
    }

    @RequiresPermissions("sys:log:log")
    @ResponseBody
    @PostMapping("/list")
    public Page<Log> list(Model model, Log log) {
        Page<Log> page = getPage();
        return logService.listPage(page, log);
    }

    @RequestMapping("/detail/{id}")
    @GetMapping
    public String detail(ModelMap map, @PathVariable("id") String id) {
        map.addAttribute("log", logService.selectById(id));
        return PREFIX + "/detail";
    }

    @RequiresPermissions("sys:log:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap map, @PathVariable("id") String id) {
        logService.deleteById(id);
        return Response.success("删除日志成功");
    }

    @RequiresPermissions("sys:log:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap map, @RequestParam("ids[]") String[] ids) {
        List<String> idList = Arrays.asList(ids);
        logService.deleteBatchIds(idList);
        return Response.success("删除日志成功");
    }

}
