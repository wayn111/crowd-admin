package com.wayn.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.annotation.Log;
import com.wayn.common.base.BaseController;
import com.wayn.common.domain.Logininfor;
import com.wayn.common.enums.Operator;
import com.wayn.common.service.LogininforService;
import com.wayn.common.util.ParameterUtil;
import com.wayn.common.util.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/system/logininfor")
public class LogininforController extends BaseController {
    private static final String PREFIX = "system/logininfor";

    @Autowired
    private LogininforService logininforService;

    @RequiresPermissions("sys:logininfor:logininfor")
    @GetMapping
    public String log(ModelMap map) {
        return PREFIX + "/logininfor";
    }

    @RequiresPermissions("sys:logininfor:logininfor")
    @ResponseBody
    @PostMapping("/list")
    public Page<Logininfor> list(Model model, Logininfor logininfor) {
        Page<Logininfor> page = getPage();
        // 设置通用查询字段
        ParameterUtil.setWrapper();
        return logininforService.listPage(page, logininfor);
    }

    @Log(value = "登陆日志管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:logininfor:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap map, @PathVariable("id") Long id) {
        return Response.result(logininforService.removeById(id), "删除登陆日志成功");
    }

    @Log(value = "登陆日志管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:logininfor:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap map, @RequestParam("ids[]") Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        return Response.result(logininforService.removeByIds(idList), "删除登陆日志成功");
    }

    @PostMapping("/export")
    public void export(Logininfor log, HttpServletResponse response) throws IOException {
        ParameterUtil.setWrapper();
        logininforService.export(log, response, request);
    }
}
