package com.wayn.web.controller.home;

import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.domain.Menu;
import com.wayn.commom.service.CacheManagerService;
import com.wayn.commom.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController extends BaseControlller {

    private static final String HOME_PREFIX = "home" ;
    private static final String MAIN_PREFIX = "main" ;
    @Autowired
    private MenuService menuService;

    @Autowired
    private CacheManagerService cacheService;

    @GetMapping
    public String index(Model model) throws Exception {
        List<Menu> treeMenus = menuService.selectTreeMenuByUserId(getCurUserId());
        model.addAttribute("treeMenus", treeMenus);
        model.addAttribute("user", getCurUser());
        return HOME_PREFIX + "/home" ;
    }

    @GetMapping("/mainIndex")
    public String mainIndex(Model model) {
        return MAIN_PREFIX + "/main" ;
    }

    @GetMapping("/mainIndex1")
    public String mainIndex1(Model model) {
        return MAIN_PREFIX + "/main1" ;
    }

}
