package com.wayn.web.controller.home;

import com.tencentcloudapi.cdn.v20180606.models.DescribeCdnDataResponse;
import com.tencentcloudapi.cdn.v20180606.models.ListTopDataResponse;
import com.tencentcloudapi.cdn.v20180606.models.TimestampData;
import com.wayn.common.base.BaseController;
import com.wayn.common.domain.Menu;
import com.wayn.common.domain.vo.EchartVO;
import com.wayn.common.enums.tencentcloud.cdn.FilterEnum;
import com.wayn.common.enums.tencentcloud.cdn.MetricEnum;
import com.wayn.common.service.ConfigService;
import com.wayn.common.service.LogService;
import com.wayn.common.service.LogininforService;
import com.wayn.common.service.MenuService;
import com.wayn.common.util.Arith;
import com.wayn.common.util.Response;
import com.wayn.tencentcloudapi.cdn.datasearch.service.CdnDataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

    private static final String MAIN_PREFIX = "main";

    @Autowired
    private ConfigService configService;

    @Autowired
    private LogininforService logininforService;

    @Autowired
    private CdnDataSearchService cdnDataSearchService;

    @Autowired
    private LogService logService;


    @GetMapping
    public String index(Model model) {
        model.addAttribute("sysName", configService.getValueByKey("sys.name"));
        return MAIN_PREFIX + "/main";
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


    @ResponseBody
    @GetMapping("/flowUseStatistic")
    public Response flowUseStatistic(Model model) {
        Response success = Response.success();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startTime = now.minus(7, ChronoUnit.DAYS).format(formatter);
        String endTime = now.format(formatter);
        List<CompletableFuture<Void>> list = new ArrayList<>();
        CompletableFuture<Void> completableFuture1 = CompletableFuture.supplyAsync(() ->
                        cdnDataSearchService.topDataSearch(startTime, endTime, MetricEnum.HOST.getLowerName(), FilterEnum.FLUX.getLowerName()))
                .thenAccept(data -> {
                    ListTopDataResponse response = (ListTopDataResponse) data;
                    Float flow = response.getData()[0].getDetailData()[0].getValue();
                    success.add("totalFlow", Arith.div(flow, 4, 1024, 1024, 1024));
                });
        CompletableFuture<Void> completableFuture2 = CompletableFuture.supplyAsync(() ->
                        cdnDataSearchService.topDataSearch(startTime, endTime, MetricEnum.HOST.getLowerName(), FilterEnum.FLUXHITRATE.getLowerName()))
                .thenAccept(data -> {
                    ListTopDataResponse response = (ListTopDataResponse) data;
                    Float avgTrafficHitRate = response.getData()[0].getDetailData()[0].getValue();
                    success.add("avgTrafficHitRate", avgTrafficHitRate);
                });
        CompletableFuture<Void> completableFuture3 = CompletableFuture.supplyAsync(() ->
                        cdnDataSearchService.accessDataSearch(startTime, endTime, "flux"))
                .thenAccept(data -> {
                    DescribeCdnDataResponse response = (DescribeCdnDataResponse) data;
                    TimestampData[] detailData = response.getData()[0].getCdnData()[0].getDetailData();
                    List<String> timeList = Arrays.stream(detailData).map(TimestampData::getTime).collect(Collectors.toList());
                    List<Double> valueList = Arrays.stream(detailData).map(timestampData -> Arith.div(timestampData.getValue(), 4, 1024, 1024, 1024)).collect(Collectors.toList());
                    success.add("timeList", timeList);
                    success.add("valueList", valueList);
                });
        list.add(completableFuture1);
        list.add(completableFuture2);
        list.add(completableFuture3);
        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();
        return success;
    }

}
