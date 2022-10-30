package com.wayn.web.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.annotation.Log;
import com.wayn.common.base.BaseController;
import com.wayn.common.domain.Dict;
import com.wayn.common.enums.Operator;
import com.wayn.common.service.DictService;
import com.wayn.common.util.ParameterUtil;
import com.wayn.common.util.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 字典管理
 */
@RequestMapping("/common/dict/type")
@Controller
public class DictTypeController extends BaseController {

    private static final String PREFIX = "common/dict/type";

    @Autowired
    private DictService dictService;

    @RequiresPermissions("common:dict:type")
    @GetMapping
    public String typeIndex() {
        return PREFIX + "/type";
    }

    @Log(value = "字典管理")
    @RequiresPermissions("common:dict:type")
    @ResponseBody
    @PostMapping("/list")
    public Page<Dict> list(Model model, Dict dict) {
        Page<Dict> page = getPage();
        //设置通用查询字段
        ParameterUtil.setWrapper();
        return dictService.listPage(page, dict);
    }

    @RequiresPermissions("common:dict:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.put("type", 1);
        return PREFIX + "/add";
    }

    @RequiresPermissions("common:dict:edit")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Dict dict = dictService.getById(id);
        modelMap.put("dict", dict);
        return PREFIX + "/edit";
    }

    @Log(value = "字典管理",operator = Operator.ADD)
    @RequiresPermissions("common:dict:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, Dict dict) {
        dictService.save(dict);
        return Response.success("新增字典分类成功");
    }

    @Log(value = "字典管理",operator = Operator.UPDATE)
    @RequiresPermissions("common:dict:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, Dict dict) {
        dictService.updateById(dict);
        return Response.success("修改字典分类成功");
    }

    @Log(value = "字典管理",operator = Operator.DELETE)
    @RequiresPermissions("common:dict:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) {
        dictService.remove(id);
        return Response.success("删除字典分类成功");
    }

    @Log(value = "字典管理",operator = Operator.DELETE)
    @RequiresPermissions("common:dict:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) {
        dictService.batchRemove(ids);
        return Response.success("删除字典分类成功");
    }

    @ResponseBody
    @PostMapping("/exists")
    public boolean exists(ModelMap modelMap, Dict dict) {
        return !dictService.exists(dict);
    }
}
