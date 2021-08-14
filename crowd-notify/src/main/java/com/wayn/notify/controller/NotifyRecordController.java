package com.wayn.notify.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.annotation.Log;
import com.wayn.common.base.BaseController;
import com.wayn.common.enums.Operator;
import com.wayn.common.service.UserService;
import com.wayn.common.util.ParameterUtil;
import com.wayn.common.util.Response;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.domain.NotifyRecordTip;
import com.wayn.notify.domain.vo.NotifyRecordVO;
import com.wayn.notify.service.NotifyRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/oa/notifyRecord")
@Controller
public class NotifyRecordController extends BaseController {

    private static final String PREFIX = "oa/notifyRecord";

    @Autowired
    private NotifyRecordService notifyRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequiresPermissions("oa:notifyRecord:list")
    @GetMapping
    public String NotifyRecordIndex(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.selectUser2JsonObj());
        return PREFIX + "/notifyRecord";
    }

    @Log(value = "我的通知")
    @RequiresPermissions("oa:notifyRecord:list")
    @ResponseBody
    @PostMapping("/list")
    public Page<NotifyRecordVO> list(Model model, NotifyRecordVO notifyRecordVO) {
        notifyRecordVO.setReceiveUserId(getCurUserId());
        Page<NotifyRecordVO> page = getPage();
        //设置通用查询字段
        ParameterUtil.set(notifyRecordVO);
        return notifyRecordService.selectNotifyRecordList(page, notifyRecordVO);
    }

    @RequiresPermissions("oa:notifyRecord:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return PREFIX + "/add";
    }

    @RequiresPermissions("oa:notifyRecord:list")
    @GetMapping("/{option}/{id}")
    public String option(ModelMap modelMap, @PathVariable("option") String option, @PathVariable("id") Long id) {
        NotifyRecordVO notifyRecordVO = notifyRecordService.selectNotifyByNotifyRecordId(id);
        // 将状态变为已读
        if (!notifyRecordVO.getRead()) {
            notifyRecordService.update().set("isRead", 1).eq("id", id).update();
        }
        simpMessagingTemplate.convertAndSendToUser(getCurUserId(), "/queue/notifyRecordTip", "");
        modelMap.put("notifyRecordVO", notifyRecordVO);
        return PREFIX + "/" + option;
    }

    @RequiresPermissions("oa:notifyRecord:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, NotifyRecord notifyRecord) {
        return Response.result(notifyRecordService.save(notifyRecord), "新增成功");
    }

    @RequiresPermissions("oa:notifyRecord:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, NotifyRecord notifyRecord) {
        return Response.result(notifyRecordService.update(notifyRecord), "修改成功");
    }


    @Log(value = "我的通知", operator = Operator.DELETE)
    @RequiresPermissions("oa:notifyRecord:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) {
        return Response.result(notifyRecordService.remove(id), "删除成功");
    }

    @Log(value = "我的通知", operator = Operator.DELETE)
    @RequiresPermissions("oa:notifyRecord:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) {
        return Response.result(notifyRecordService.batchRemove(ids), "删除成功");
    }

    @PostMapping("/export")
    public void export(NotifyRecordVO notifyRecordVO, HttpServletResponse response) throws IOException {
        notifyRecordVO.setReceiveUserId(getCurUserId());
        //设置通用查询字段
        ParameterUtil.set(notifyRecordVO);
        notifyRecordService.export(notifyRecordVO, response, request);
    }

    /**
     * 获取右上角通知图标最新状态
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/notifyRecordTip")
    public Page<NotifyRecordTip> notifyRecordTip() {
        Page<NotifyRecordTip> page = getPage(1, 5);
        return notifyRecordService.selectNotifyRecordTipList(page, getCurUserId());
    }

}
