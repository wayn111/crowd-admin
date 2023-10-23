package com.wayn.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.annotation.Log;
import com.wayn.common.base.BaseController;
import com.wayn.common.domain.Config;
import com.wayn.common.enums.Operator;
import com.wayn.common.service.ConfigService;
import com.wayn.common.service.DictService;
import com.wayn.common.shiro.util.ShiroUtil;
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
import java.util.Date;

/**
 * 参数配置 控制层
 *
 * @author wayn
 * @date 2020-09-16
 */
@RequestMapping("/system/config")
@Controller
public class ConfigController extends BaseController {

    private static final String PREFIX = "system/config";

    @Autowired
    private ConfigService configService;

    @Autowired
    private DictService dictService;

    @RequiresPermissions("sys:config:config")
    @GetMapping
    public String ConfigIndex(Model model) {
        model.addAttribute("sysBuildIn", dictService.selectDictsValueByType("sysBuildIn"));
        return PREFIX + "/config";
    }

    @Log(value = "参数管理")
    @RequiresPermissions("sys:config:config")
    @ResponseBody
    @PostMapping("/list")
    public Page<Config> list(Model model, Config config) {
        Page<Config> page = getPage();
        ParameterUtil.set(config);
        return configService.selectConfigList(page, config);
    }

    @RequiresPermissions("sys:config:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("sysBuildIn", dictService.selectDictsValueByType("sysBuildIn", "Y"));
        return PREFIX + "/add";
    }

    @RequiresPermissions("sys:config:edit")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Config config = configService.getById(id);
        modelMap.put("config", config);
        modelMap.addAttribute("sysBuildIn", dictService.selectDictsValueByType("sysBuildIn"));
        return PREFIX + "/edit";
    }

    @Log(value = "参数管理", operator = Operator.ADD)
    @RequiresPermissions("sys:config:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, Config config) {
        config.setCreateTime(new Date());
        config.setCreateBy(ShiroUtil.getSessionUser().getUserName());
        return Response.result(configService.saveConfig(config), "新增成功");
    }

    @Log(value = "参数管理", operator = Operator.UPDATE)
    @RequiresPermissions("sys:config:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, Config config) {
        config.setUpdateTime(new Date());
        config.setUpdateBy(ShiroUtil.getSessionUser().getUserName());
        return Response.result(configService.updateConfig(config), "修改成功");
    }


    @Log(value = "参数管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:config:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Integer id) {
        return Response.result(configService.removeById(id), "删除成功");
    }

    @Log(value = "参数管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:config:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Integer[] ids) {
        return Response.result(configService.removeByIds(Arrays.asList(ids)), "删除成功");
    }

    @Log(value = "参数管理", operator = Operator.EXECUTOR)
    @PostMapping("/export")
    public void export(Config config, HttpServletResponse response) throws IOException {
        configService.export(config, response, request);
    }

}
