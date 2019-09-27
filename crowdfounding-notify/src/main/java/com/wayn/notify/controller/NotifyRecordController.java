package com.wayn.notify.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.annotation.Log;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.enums.Operator;
import com.wayn.commom.service.UserService;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.util.Response;
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

@RequestMapping("/oa/notifyRecord")
@Controller
public class NotifyRecordController extends BaseControlller {

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
            notifyRecordService.updateForSet("isRead = 1", new EntityWrapper<NotifyRecord>().eq("id", id));
        }
        simpMessagingTemplate.convertAndSendToUser(getCurUserId(), "/queue/notifyRecordTip", "");
        modelMap.put("notifyRecordVO", notifyRecordVO);
        return PREFIX + "/" + option;
    }

    @RequiresPermissions("oa:notifyRecord:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, NotifyRecord notifyRecord) {
        notifyRecordService.save(notifyRecord);
        return Response.success("新增成功");
    }

    @RequiresPermissions("oa:notifyRecord:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, NotifyRecord notifyRecord) {
        notifyRecordService.update(notifyRecord);
        return Response.success("修改成功");
    }


    @Log(value = "我的通知", operator = Operator.DELETE)
    @RequiresPermissions("oa:notifyRecord:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) {
        notifyRecordService.remove(id);
        return Response.success("删除成功");
    }

    @Log(value = "我的通知", operator = Operator.DELETE)
    @RequiresPermissions("oa:notifyRecord:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) {
        notifyRecordService.batchRemove(ids);
        return Response.success("删除成功");
    }

    @ResponseBody
    @GetMapping("/notifyRecordTip")
    public Page<NotifyRecordTip> notifyRecordTip() {
        Page<NotifyRecordTip> page = getPage(1, 5);
        Page<NotifyRecordTip> list = notifyRecordService.selectNotifyRecordTipList(page, getCurUserId());
        return list;
    }

}
