package com.wayn.controller.commom;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.util.Response;
import com.wayn.domain.Dict;
import com.wayn.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/commom/dict/data")
@Controller
public class DictDataController extends BaseControlller {

    private static final String PREFIX = "commom/dict/data";

    @Autowired
    private DictService dictService;

    @RequiresPermissions("commom:dict:type")
    @GetMapping("/{dictType}")
    public String dataIndex(ModelMap modelMap, @PathVariable("dictType") String dictType) {
        modelMap.put("dictType", dictType);
        List<JSONObject> objectList = dictService.selectDicts(dictType);
        modelMap.put("dicts", objectList);
        return PREFIX + "/data";
    }

    @RequiresPermissions("commom:dict:type")
    @ResponseBody
    @PostMapping("/list")
    public Page<Dict> list(Model model, Dict dict) {
        Page<Dict> page = getPage();
        return dictService.listPage(page, dict);
    }

    @RequiresPermissions("commom:dict:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.put("type", 2);
        return PREFIX + "/add";
    }

    @RequiresPermissions("commom:dict:edit")
    @GetMapping("/edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable("id") Long id) {
        Dict dict = dictService.selectById(id);
        modelMap.put("dict", dict);
        return PREFIX + "/edit";
    }

    @RequiresPermissions("commom:dict:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, Dict dict) {
        dictService.save(dict);
        return Response.success("新增字典数据成功");
    }

    @RequiresPermissions("commom:dict:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, Dict dict) {
        dictService.update(dict);
        return Response.success("修改字典数据成功");
    }


    @RequiresPermissions("commom:dict:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) {
        dictService.remove(id);
        return Response.success("删除字典数据成功");
    }

    @RequiresPermissions("commom:dict:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) {
        dictService.batchRemove(ids);
        return Response.success("删除字典数据成功");
    }

    @ResponseBody
    @PostMapping("/exists")
    public boolean exists(ModelMap modelMap, Dict dict) {
        return !dictService.exists(dict);
    }
}
