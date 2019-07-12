package com.wayn.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.domain.TableInfo;
import com.wayn.service.GenService;

@Controller
@RequestMapping("/tool/gen")
public class GenController extends BaseControlller {

	private static final String PREFIX = "tool/gen";

	@Autowired
	private GenService genService;

	@GetMapping
	public String genIndex() {
		return PREFIX + "/gen";
	}

	@RequiresPermissions("tool:gen:list")
	@ResponseBody
	@PostMapping("/list")
	public Page<TableInfo> list(Model model, TableInfo tableInfo) {
		Page<TableInfo> page = getPage();
		Page<TableInfo> tableInfoPage = genService.selectTableList(page, tableInfo);
		return tableInfoPage;
	}

}
