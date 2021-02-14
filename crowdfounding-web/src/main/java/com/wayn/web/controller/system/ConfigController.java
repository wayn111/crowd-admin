package com.wayn.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.commom.base.BaseController;
import com.wayn.commom.domain.Config;
import com.wayn.commom.service.ConfigService;
import com.wayn.commom.service.DictService;
import com.wayn.commom.shiro.util.ShiroUtil;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.util.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @RequiresPermissions("sys:config:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, Config config) {
        config.setCreateTime(new Date());
        config.setCreateBy(ShiroUtil.getSessionUser().getUserName());
        return Response.result(configService.save(config), "新增成功");
    }

    @RequiresPermissions("sys:config:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, Config config) {
        config.setUpdateTime(new Date());
        config.setUpdateBy(ShiroUtil.getSessionUser().getUserName());
        return Response.result(configService.update(config), "修改成功");
    }


    @RequiresPermissions("sys:config:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Integer id) {
        return Response.result(configService.remove(id), "删除成功");
    }

    @RequiresPermissions("sys:config:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Integer[] ids) {
        return Response.result(configService.batchRemove(ids), "删除成功");
    }

    @PostMapping("/export")
    public void export(Config config, HttpServletResponse response) throws IOException {
        configService.export(config, response, request);
    }

}
