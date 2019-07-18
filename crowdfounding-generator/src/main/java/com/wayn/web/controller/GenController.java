package com.wayn.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.domain.TableInfo;
import com.wayn.commom.enums.Operator;
import com.wayn.commom.service.GenService;
import com.wayn.framework.annotation.Log;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/tool/gen")
public class GenController extends BaseControlller {

    private static final String PREFIX = "tool/gen";

    @Autowired
    private GenService genService;

    @RequiresPermissions("tool:gen:list")
    @GetMapping
    public String genIndex() {
        return PREFIX + "/gen";
    }

    @Log(value = "代码生成")
    @RequiresPermissions("tool:gen:list")
    @ResponseBody
    @PostMapping("/list")
    public Page<TableInfo> list(Model model, TableInfo tableInfo) {
        Page<TableInfo> page = getPage();
        Page<TableInfo> tableInfoPage = genService.selectTableList(page, tableInfo);
        return tableInfoPage;
    }

    @Log(value = "代码生成", operator = Operator.GENCODE)
    @RequiresPermissions("tool:gen:gen")
    @GetMapping("/genCode/{name}")
    public void genCode(@PathVariable("name") String tableName, HttpServletResponse response) throws IOException {
        byte[] data = genService.generatorCode(tableName);
        genCode(response, data);
    }

    @Log(value = "代码生成", operator = Operator.GENCODE)
    @RequiresPermissions("tool:gen:gen")
    @GetMapping("/batchGenCode")
    public void batchGenCode(String names, HttpServletResponse response) throws IOException {
        byte[] data = genService.generatorCode(names.split(",", -1));
        genCode(response, data);
    }

    /**
     * 生成zip文件
     *
     * @param response
     * @param data
     * @throws IOException
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"crowd.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

}
