package com.wayn.notify.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.service.UserService;
import com.wayn.commom.util.DateUtils;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.util.Response;
import com.wayn.notify.domain.Notify;
import com.wayn.notify.domain.NotifyRecord;
import com.wayn.notify.domain.vo.NotifyVO;
import com.wayn.notify.service.NotifyRecordService;
import com.wayn.notify.service.NotifyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class NotifyController extends BaseControlller {

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
    public String edit(ModelMap modelMap, @PathVariable("option") String option, @PathVariable("id") Long id) {
        // 查询通知
        Notify notify = notifyService.selectById(id);
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
        List<NotifyRecord> records = notifyRecordService.selectList(new EntityWrapper<NotifyRecord>().eq("notifyId", notifyVO.getId()));
        List<String> receiveUserIds = records.stream().map(item -> {
            return item.getReceiveUserId();
        }).collect(Collectors.toList());
        List<String> receiveUserNames = records.stream().map(item -> {
            return item.getReceiveUserName();
        }).collect(Collectors.toList());
        modelMap.put("receiveUserIds", StringUtils.join(receiveUserIds, ","));
        modelMap.put("receiveUserNames", StringUtils.join(receiveUserNames, ","));
        return PREFIX + "/" + option;
    }

    @RequiresPermissions("oa:notify:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, NotifyVO notifyVO, String receiveUserIds) throws ParseException {
        Notify notify = getNotify(notifyVO);
        if (StringUtils.isEmpty(receiveUserIds)) {
            receiveUserIds = "";
        }
        notifyService.save(notify, receiveUserIds);
        return Response.success("新增成功");
    }

    @RequiresPermissions("oa:notify:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, NotifyVO notifyVO, String receiveUserIds) throws ParseException {
        Notify notify = getNotify(notifyVO);
        if (StringUtils.isEmpty(receiveUserIds)) {
            receiveUserIds = "";
        }
        notifyService.update(notify, receiveUserIds);
        return Response.success("修改成功");
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


    @RequiresPermissions("oa:notify:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) {
        notifyService.remove(id);
        return Response.success("删除成功");
    }

    @RequiresPermissions("oa:notify:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) {
        notifyService.batchRemove(ids);
        return Response.success("删除成功");
    }

}
