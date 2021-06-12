package com.wayn.web.controller.monitor;

import com.wayn.commom.annotation.Log;
import com.wayn.framework.web.domian.Server;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/server")
public class ServerController {
    private static final String PREFIX = "monitor/server";

    @Log(value = "服务监控")
    @RequiresPermissions("monitor:server:server")
    @GetMapping
    public String systemIndex(ModelMap map) throws Exception {
        Server server = new Server();
        server.copyTo();
        map.put("server", server);
        return PREFIX + "/server";
    }
}
