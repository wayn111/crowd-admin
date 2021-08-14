package com.wayn.notify.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wayn.common.annotation.Log;
import com.wayn.common.base.BaseController;
import com.wayn.common.enums.Operator;
import com.wayn.common.service.UserService;
import com.wayn.common.util.DateUtils;
import com.wayn.common.util.ParameterUtil;
import com.wayn.common.util.Response;
import com.wayn.notify.domain.Notify;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.domain.vo.NotifyVO;
import com.wayn.notify.service.NotifyRecordService;
import com.wayn.notify.service.NotifyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/oa/notify")
@Controller
public class NotifyController extends BaseController {

    private static final String PREFIX = "oa/notify";

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private NotifyRecordService notifyRecordService;

    @Autowired
    private UserService userService;

    @RequiresPermissions("oa:notify:list")
    @GetMapping
    public String NotifyIndex(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.selectUser2JsonObj());
        return PREFIX + "/notify";
    }

    @Log(value = "办公通知")
    @RequiresPermissions("oa:notify:list")
    @ResponseBody
    @PostMapping("/list")
    public Page<Notify> list(Model model, Notify notify) {
        Page<Notify> page = getPage();
        //设置通用查询字段
        ParameterUtil.set(notify);
        return notifyService.selectNotifyList(page, notify);
    }

    @RequiresPermissions("oa:notify:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return PREFIX + "/add";
    }

    @RequiresPermissions("oa:notify:edit")
    @GetMapping("/{option}/{id}")
    public String option(ModelMap modelMap, @PathVariable("option") String option, @PathVariable("id") Long id) {
        // 查询通知
        Notify notify = notifyService.getById(id);
        NotifyVO notifyVO = new NotifyVO();
        if (notify.getPublishTime() != null) {
            notifyVO.setPublishTime(DateFormatUtils.format(notify.getPublishTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        BeanUtils.copyProperties(notify, notifyVO, "publishTime", "createTime", "updateTime");
        // 对content内容进行转义
        String s = notifyVO.getContent().replaceAll("&quot;", "&wayn;");
        notifyVO.setContent(s);
        modelMap.put("notify", notifyVO);
        // 查询接收用户
        List<NotifyRecord> records = notifyRecordService.list(new QueryWrapper<NotifyRecord>().eq("notifyId", notifyVO.getId()));
        List<String> receiveUserIds = records.stream().map(NotifyRecord::getReceiveUserId).collect(Collectors.toList());
        List<String> receiveUserNames = records.stream().map(NotifyRecord::getReceiveUserName).collect(Collectors.toList());
        modelMap.put("receiveUserIds", StringUtils.join(receiveUserIds, ","));
        modelMap.put("receiveUserNames", StringUtils.join(receiveUserNames, ","));
        return PREFIX + "/" + option;
    }

    @Log(value = "办公通知", operator = Operator.ADD)
    @RequiresPermissions("oa:notify:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, NotifyVO notifyVO, String receiveUserIds) throws ParseException {
        Notify notify = getNotify(notifyVO);
        if (StringUtils.isBlank(receiveUserIds)) {
            receiveUserIds = "";
        }
        return Response.result(notifyService.save(notify, receiveUserIds), "新增成功");
    }

    @Log(value = "办公通知", operator = Operator.UPDATE)
    @RequiresPermissions("oa:notify:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, NotifyVO notifyVO, String receiveUserIds) throws ParseException, SchedulerException {
        Notify notify = getNotify(notifyVO);
        if (StringUtils.isBlank(receiveUserIds)) {
            receiveUserIds = "";
        }
        return Response.result(notifyService.update(notify, receiveUserIds), "修改成功");
    }

    /**
     * 将notifyVO转为notify对象
     *
     * @param notifyVO
     * @return
     * @throws ParseException
     */
    public Notify getNotify(NotifyVO notifyVO) throws ParseException {
        Notify notify = getNotify();
        if (StringUtils.isNotEmpty(notifyVO.getPublishTime())) {
            notify.setPublishTime(DateUtils.parseDate(notifyVO.getPublishTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        BeanUtils.copyProperties(notifyVO, notify, "publishTime");
        return notify;
    }

    public Notify getNotify() {
        return new Notify();
    }


    @Log(value = "办公通知", operator = Operator.DELETE)
    @RequiresPermissions("oa:notify:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) throws SchedulerException {
        return Response.result(notifyService.remove(id), "删除成功");
    }

    @Log(value = "办公通知", operator = Operator.DELETE)
    @RequiresPermissions("oa:notify:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) throws SchedulerException {
        return Response.result(notifyService.batchRemove(ids), "删除成功");
    }

}
