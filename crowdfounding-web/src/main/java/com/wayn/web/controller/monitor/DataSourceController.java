package com.wayn.web.controller.monitor;

import com.wayn.commom.annotation.Log;
import com.wayn.commom.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/datasource")
public class DataSourceController extends BaseController {

	@Log(value = "数据源监控")
	@RequiresPermissions("monitor:datasource:datasource")
	@GetMapping
	public String monitorindex(Model model) {
		return redirectTo("/druid");
	}
}
