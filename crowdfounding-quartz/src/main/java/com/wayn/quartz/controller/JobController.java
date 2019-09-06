package com.wayn.quartz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.service.DictService;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.util.Response;
import com.wayn.quartz.domain.Job;
import com.wayn.quartz.service.JobService;
import com.wayn.quartz.util.CronUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/quartz/job")
@Controller
public class JobController extends BaseControlller {

    private static final String PREFIX = "quartz/job";

    @Autowired
    private JobService jobService;

    @Autowired
    private DictService dictService;

    @RequiresPermissions("quartz:job:list")
    @GetMapping
    public String JobIndex() {
        return PREFIX + "/job";
    }

    @RequiresPermissions("quartz:job:list")
    @ResponseBody
    @PostMapping("/list")
    public Page<Job> list(Model model, Job job) {
        Page<Job> page = getPage();
        ParameterUtil.set(job);
        return jobService.selectJobList(page, job);
    }

    @RequiresPermissions("quartz:job:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("misfirePolicys", dictService.selectDictsValueByType("misfirePolicy"));
        return PREFIX + "/add";
    }

    @RequiresPermissions("quartz:job:edit")
    @GetMapping("/{option}/{id}")
    public String edit(ModelMap modelMap, @PathVariable("option") String option, @PathVariable("id") Long id) {
        Job job = jobService.selectById(id);
        modelMap.put("job", job);
        modelMap.addAttribute("misfirePolicys", dictService.selectDictsValueByType("misfirePolicy"));
        return PREFIX + "/" + option;
    }

    @RequiresPermissions("quartz:job:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(ModelMap modelMap, Job job) {
        jobService.save(job);
        return Response.success("新增成功");
    }

    @RequiresPermissions("quartz:job:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(ModelMap modelMap, Job job) throws SchedulerException {
        jobService.update(job);
        return Response.success("修改成功");
    }

    @RequiresPermissions("quartz:job:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(ModelMap modelMap, @PathVariable("id") Long id) throws SchedulerException {
        jobService.remove(id);
        return Response.success("删除成功");
    }

    @RequiresPermissions("quartz:job:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(ModelMap modelMap, @RequestParam("ids[]") Long[] ids) throws SchedulerException {
        jobService.batchRemove(ids);
        return Response.success("删除成功");
    }


    @RequiresPermissions("quartz:job:executor")
    @ResponseBody
    @PostMapping("/changeStatus/{id}")
    public Response changeStatus(ModelMap modelMap, @PathVariable("id") Long id) throws SchedulerException {
        Job job = jobService.selectById(id);
        job.setJobState(job.getJobState() == 1 ? -1 : 1);
        jobService.changeStatus(job);
        return Response.success("修改成功");
    }

    @RequiresPermissions("quartz:job:executor")
    @ResponseBody
    @PostMapping("/run/{id}")
    public Response run(ModelMap modelMap, @PathVariable("id") Long id) throws SchedulerException {
        jobService.run(id);
        return Response.success("删除成功");
    }


    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }
}
