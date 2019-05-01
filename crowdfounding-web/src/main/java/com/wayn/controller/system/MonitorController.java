package com.wayn.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wayn.commom.base.BaseControlller;

@Controller
@RequestMapping("/system/monitor")
public class MonitorController extends BaseControlller {

	@GetMapping
	public String monitorindex(Model model) {
		return redirectTo("/druid");
	}
}
