package com.wayn.web.controller.system;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.commom.annotation.Log;
import com.wayn.commom.base.BaseControlller;
import com.wayn.commom.domain.Dept;
import com.wayn.commom.domain.MailConfig;
import com.wayn.commom.domain.Role;
import com.wayn.commom.domain.User;
import com.wayn.commom.domain.vo.RoleChecked;
import com.wayn.commom.domain.vo.SendMailVO;
import com.wayn.commom.domain.vo.Tree;
import com.wayn.commom.domain.vo.UserVO;
import com.wayn.commom.enums.Operator;
import com.wayn.commom.service.*;
import com.wayn.commom.shiro.util.ShiroUtil;
import com.wayn.commom.util.AdminUtil;
import com.wayn.commom.util.HttpUtil;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.util.Response;
import com.wayn.framework.jms.queue.MailQueueProducer;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseControlller {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String PREFIX = "system/user";

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private DictService dictService;

    @Autowired
    private MailConfigService mailConfigService;

    @Autowired
    private MailQueueProducer mailQueueProducer;

    @Autowired
    private ConfigService configService;

    @RequiresPermissions("sys:user:user")
    @GetMapping
    public String userIndex(Model model) {
        model.addAttribute("states", dictService.selectDictsValueByType("state"));
        return PREFIX + "/user";
    }

    @Log(value = "用户管理")
    @RequiresPermissions("sys:user:user")
    @ResponseBody
    @PostMapping("/list")
    public Page<UserVO> list(Model model, User user) {
        Page<User> page = getPage();
        //设置通用查询字段
        ParameterUtil.setWrapper();
        return userService.listPage(page, user);
    }

    @RequiresPermissions("sys:user:add")
    @GetMapping("/add")
    public String add(Model model) {
        List<Role> list = roleService.selectList(new EntityWrapper<Role>().eq("roleState", 1));
        model.addAttribute("roles", list);
        return PREFIX + "/add";
    }

    @RequiresPermissions("sys:user:edit")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        User user = userService.selectById(id);
        model.addAttribute("user", user);
        Dept dept = deptService.selectById(user.getDeptId());
        if (Objects.nonNull(dept)) {
            String deptName = dept.getDeptName();
            model.addAttribute("deptName", deptName);
        }
        List<RoleChecked> roleCheckedList = roleService.listCheckedRolesByUid(id);
        model.addAttribute("roles", roleCheckedList);
        return PREFIX + "/edit";
    }

    @RequiresPermissions("sys:user:resetPwd")
    @GetMapping("/resetPwd/{id}")
    public String resetPwd(Model model, @PathVariable("id") String id) {
        model.addAttribute("id", id);
        return PREFIX + "/resetPwd";
    }

    @RequiresPermissions("sys:user:resetPwd")
    @ResponseBody
    @PostMapping("/resetPwd")
    public Response resetPwd(Model model, @RequestParam String id, @RequestParam String password) {
        if (AdminUtil.isAdmin(id)) {
            return Response.error("管理员账号无法操作");
        }
        userService.resetPwd(id, ShiroUtil.md5encrypt(password, userService.selectById(id).getUserName()));
        return Response.success("修改用户密码成功");
    }

    @RequiresPermissions("sys:user:editAccount")
    @GetMapping("/editAccount/{id}")
    public String editAccount(Model model, @PathVariable("id") String id) {
        model.addAttribute("id", id);
        model.addAttribute("initPassWord", configService.getValueByKey("sys.user.initPassword"));
        model.addAttribute("userName", userService.selectById(id).getUserName());
        return PREFIX + "/editAccount";
    }

    @RequiresPermissions("sys:user:editAccount")
    @ResponseBody
    @PostMapping("/editAccount")
    public Response editAccount(Model model, @RequestParam String id, @RequestParam String userName) {
        if (AdminUtil.isAdmin(id)) {
            return Response.error("管理员账号无法操作");
        }
        userService.editAccount(id, userName);
        return Response.success("修改用户名称成功");
    }

    @ResponseBody
    @PostMapping("/exists")
    public boolean exists(Model model, @RequestParam Map<String, Object> params) {
        return !userService.exit(params);
    }

    @Log(value = "用户管理", operator = Operator.ADD)
    @RequiresPermissions("sys:user:add")
    @ResponseBody
    @PostMapping("/addSave")
    public Response addSave(Model model, User user, String roleIds) {
        if (userService.save(user, roleIds)) {
            MailConfig mailConfig = mailConfigService.selectById(1L);
            if (!mailConfigService.checkMailConfig(mailConfig)) {
                return Response.error("邮件信息未配置完全，请先填写配置信息");
            }
            SendMailVO mailVO = new SendMailVO();
            mailVO.setReceiverUser(user.getUserName());
            mailVO.setSendMail(user.getEmail());
            mailVO.setTitle("欢迎：" + user.getUserName());
            mailQueueProducer.sendMail(mailConfig, mailVO);
            return Response.success("新增用户成功");
        }
        return Response.error("新增用户失败");
    }

    @Log(value = "用户管理", operator = Operator.UPDATE)
    @RequiresPermissions("sys:user:edit")
    @ResponseBody
    @PostMapping("/editSave")
    public Response editSave(Model model, User user, String roleIds) {
        if (AdminUtil.isAdmin(user.getId())) {
            return Response.error("管理员账号无法操作");
        }
        userService.update(user, roleIds);
        return Response.success("修改用户成功");

    }

    @Log(value = "用户管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:user:remove")
    @ResponseBody
    @DeleteMapping("/remove/{id}")
    public Response remove(Model model, @PathVariable("id") String id) {
        if (AdminUtil.isAdmin(id)) {
            return Response.error("管理员账号无法操作");
        }
        userService.remove(id);
        return Response.success("删除用户成功");

    }

    @Log(value = "用户管理", operator = Operator.DELETE)
    @RequiresPermissions("sys:user:remove")
    @ResponseBody
    @PostMapping("/batchRemove")
    public Response batchRemove(Model model, @RequestParam("ids[]") String[] ids) {
        for (String id : ids) {
            if (AdminUtil.isAdmin(id)) {
                return Response.error("管理员账号无法操作");
            }
        }
        userService.batchRemove(ids);
        return Response.success("删除用户成功");

    }

    @PostMapping("/export")
    public void export(User user, HttpServletResponse response) throws IOException {
        ParameterUtil.setWrapper();
        userService.export(user, response, request);
    }

    @GetMapping("/import")
    public String importExecl(Model model) {
        model.addAttribute("initPassWord", configService.getValueByKey("sys.user.initPassword"));
        return PREFIX + "/import";
    }

    @ResponseBody
    @PostMapping("/upload")
    public Response upload(@RequestParam("file") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws Exception {
        InputStream inputstream = file.getInputStream();
        ImportParams params = new ImportParams();
        List<User> list = new ExcelImportService().importExcelByIs(inputstream, User.class, params, false).getList();
        String requestUrl = HttpUtil.getRequestContext(request);
        list.forEach(item -> {
            if (StringUtils.isNotEmpty(item.getUserImg())) {
                item.setUserImg(requestUrl + "/" + item.getUserImg().substring(item.getUserImg().indexOf("upload")));
            }
            item.setDeptId(1L);
            String password = configService.getValueByKey("sys.user.initPassword");
            item.setPassword(ShiroUtil.md5encrypt(password, item.getUserName()));
        });
        userService.insertBatch(list);
        return Response.success("导入用户数据成功");
    }

    @ResponseBody
    @PostMapping("/tree")
    public Tree<Dept> tree(Model model) {
        return userService.getTree();
    }

    @GetMapping("/treeView")
    public String treeView(Model model) {
        return PREFIX + "/treeView";
    }
}
