package com.wayn.web.controller.home;

import com.wayn.commom.base.BaseController;
import com.wayn.commom.domain.Menu;
import com.wayn.commom.domain.vo.EchartVO;
import com.wayn.commom.service.ConfigService;
import com.wayn.commom.service.LogService;
import com.wayn.commom.service.LogininforService;
import com.wayn.commom.service.MenuService;
import com.wayn.commom.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

    private static final String HOME_PREFIX = "home";
    private static final String MAIN_PREFIX = "main";
    @Autowired
    private MenuService menuService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private LogininforService logininforService;

    @Autowired
    private LogService logService;

    @GetMapping
    public String index(Model model) throws Exception {
        List<Menu> treeMenus = menuService.selectTreeMenuByUserId(getCurUserId());
        model.addAttribute("treeMenus", treeMenus);
        model.addAttribute("sysName", configService.getValueByKey("sys.name"));
        model.addAttribute("sysFooter", configService.getValueByKey("sys.footer.copyright"));
        model.addAttribute("user", getCurUser());
        return HOME_PREFIX + "/home";
    }

    @GetMapping("/mainIndex")
    public String mainIndex(Model model) {
        model.addAttribute("sysName", configService.getValueByKey("sys.name"));
        return MAIN_PREFIX + "/main";
    }

    @GetMapping("/mainIndex1")
    public String mainIndex1(Model model) {
        model.addAttribute("sysName", configService.getValueByKey("sys.name"));
        return MAIN_PREFIX + "/main1";
    }

    @ResponseBody
    @GetMapping("/countryProvinceAccessCount")
    public Response countryProvinceAccessCount(Model model) {
        List<EchartVO> echartVOS = logininforService.selectLoginLocationCount();
        List<EchartVO> collect = new ArrayList<>(echartVOS.stream()
                .filter(loginLocationCountVO -> {
                    String province = loginLocationCountVO.getName().split("\\|")[2];
                    return province.length() > 2;
                })
                .map(loginLocationCountVO -> {
                    EchartVO echartVO = new EchartVO();
                    String province = loginLocationCountVO.getName().split("\\|")[2];
                    echartVO.setValue(loginLocationCountVO.getValue());
                    echartVO.setName(province);
                    return echartVO;
                })
                .collect(Collectors.toMap(EchartVO::getName, echartVO -> echartVO, (o1, o2) -> {
                    o1.setValue(o1.getValue() + o2.getValue());
                    return o1;
                })).values()).stream().sorted((o1, o2) -> (int) (o2.getValue() - o1.getValue())).collect(Collectors.toList());
        return Response.success().add("data", collect);
    }

    @ResponseBody
    @GetMapping("/moduleUseStatistic")
    public Response moduleUseStatistic(Model model) {
        List<EchartVO> list = logService.selectModuleUseStatistic();
        List<String> nameList = list.stream().map(EchartVO::getName).collect(Collectors.toList());
        List<Integer> dataList = list.stream().map(EchartVO::getValue).collect(Collectors.toList());
        return Response.success().add("nameList", nameList).add("dataList", dataList);
    }
}
