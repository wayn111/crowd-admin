package com.wayn.common.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.common.dao.RoleDao;
import com.wayn.common.dao.RoleMenuDao;
import com.wayn.common.dao.UserRoleDao;
import com.wayn.common.domain.Role;
import com.wayn.common.domain.RoleMenu;
import com.wayn.common.domain.UserRole;
import com.wayn.common.domain.vo.RoleChecked;
import com.wayn.common.excel.IExcelExportStylerImpl;
import com.wayn.common.exception.BusinessException;
import com.wayn.common.service.RoleService;
import com.wayn.common.service.UserRoleService;
import com.wayn.common.util.ParameterUtil;
import com.wayn.common.util.ServletUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleDao roleDao;

    @Override
    public Page<Role> listPage(Page<Role> page, Role role) {
        QueryWrapper<Role> wrapper = ParameterUtil.get();
        wrapper.like("roleName", role.getRoleName());
        wrapper.eq(role.getRoleState() != null, "roleState", role.getRoleState());
        return roleDao.selectPage(page, wrapper);
    }

    @Override
    public void export(Role role, HttpServletResponse response, HttpServletRequest request) throws IOException {
        QueryWrapper<Role> wrapper = ParameterUtil.get();
        wrapper.like("roleName", role.getRoleName());
        wrapper.eq(role.getRoleState() != null, "roleState", role.getRoleState());
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(IExcelExportStylerImpl.class);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        List<Role> list = list(wrapper);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Role.class, list);
        // 使用bos获取excl文件大小
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        ServletUtil.setExportResponse(request, response, "角色列表.xlsx", bos.size());
        //保存数据
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.close();
        bos.close();
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional
    @Override
    public boolean save(Role role, String menuIds) {
        boolean flag = super.save(role);
        List<RoleMenu> list = new ArrayList<>();
        if (StringUtils.isNotBlank(menuIds)) {
            String[] split = menuIds.split(",");
            for (String menuId : split) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(role.getId());
                list.add(roleMenu);
            }
        }
        roleMenuDao.delete(new QueryWrapper<RoleMenu>().eq("roleId", role.getId()));
        if (list.size() > 0) {
            roleMenuDao.batchSave(list);
        }
        return flag;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional()
    @Override
    public boolean update(Role role, String menuIds) throws Exception {
        boolean flag = updateById(role);
        List<RoleMenu> list = new ArrayList<>();
        if (StringUtils.isNotBlank(menuIds)) {
            String[] split = menuIds.split(",");
            for (String menuId : split) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(role.getId());
                list.add(roleMenu);
            }
        }
        roleMenuDao.delete(new QueryWrapper<RoleMenu>().eq("roleId", role.getId()));
        if (list.size() > 0) {
            roleMenuDao.batchSave(list);
        }
        return flag;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional
    @Override
    public boolean remove(String roleId) throws BusinessException {
        if (userRoleDao.selectList(new QueryWrapper<UserRole>().eq("roleId", roleId)).size() > 0) {
            throw new BusinessException("该角色有绑定用户，请先解绑");
        }
        removeById(roleId);
        roleMenuDao.delete(new QueryWrapper<RoleMenu>().eq("roleId", roleId));
        userRoleDao.delete(new QueryWrapper<UserRole>().eq("roleId", roleId));
        return true;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Override
    public boolean batchRemove(String[] ids) throws BusinessException {
        for (String id : ids) {
            if (userRoleDao.selectList(new QueryWrapper<UserRole>().eq("roleId", id)).size() > 0) {
                throw new BusinessException("该角色有绑定用户，请先解绑");
            }
        }
        return removeByIds(Arrays.asList(ids));
    }

    /**
     * 设置当前用户的角色checkbox
     *
     * @param uid 当前用户id
     */
    @Override
    public List<RoleChecked> listCheckedRolesByUid(String uid) {
        List<Role> list = list(new QueryWrapper<Role>().eq("roleState", 1));
        Set<String> sets = userRoleService.findRolesByUid(uid);
        return list.stream().map(role -> {
            RoleChecked checked = new RoleChecked();
            BeanUtils.copyProperties(role, checked);
            sets.forEach(item -> {
                if (item.equals(checked.getId())) {
                    checked.setChecked(true);
                }
            });
            return checked;
        }).collect(Collectors.toList());
    }

}
