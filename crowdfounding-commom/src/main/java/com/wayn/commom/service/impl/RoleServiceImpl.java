package com.wayn.commom.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.dao.RoleDao;
import com.wayn.commom.dao.RoleMenuDao;
import com.wayn.commom.dao.UserRoleDao;
import com.wayn.commom.domain.Role;
import com.wayn.commom.domain.UserRole;
import com.wayn.commom.exception.BusinessException;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.domain.RoleMenu;
import com.wayn.commom.domain.vo.RoleChecked;
import com.wayn.commom.service.RoleService;
import com.wayn.commom.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Page<Role> listPage(Page<Role> page, Role role) {
        EntityWrapper<Role> wrapper = ParameterUtil.get();
        wrapper.like("roleName", role.getRoleName());
        wrapper.eq(role.getRoleState() != null, "roleState", role.getRoleState());
        Page<Role> selectPage = selectPage(page, wrapper);
        return selectPage;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional
    @Override
    public boolean save(Role role, String menuIds) {
        boolean flag = insert(role);
        List<RoleMenu> list = new ArrayList<RoleMenu>();
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
        roleMenuDao.delete(new EntityWrapper<RoleMenu>().eq("roleId", role.getId()));
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
        List<RoleMenu> list = new ArrayList<RoleMenu>();
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
        roleMenuDao.delete(new EntityWrapper<RoleMenu>().eq("roleId", role.getId()));
        if (list.size() > 0) {
            roleMenuDao.batchSave(list);
        }
        return flag;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Transactional
    @Override
    public boolean remove(String roleId) throws BusinessException {
        if (userRoleDao.selectList(new EntityWrapper<UserRole>().eq("roleId", roleId)).size() > 0) {
            throw new BusinessException("该角色有绑定用户，请先解绑");
        }
        deleteById(roleId);
        roleMenuDao.delete(new EntityWrapper<RoleMenu>().eq("roleId", roleId));
        userRoleDao.delete(new EntityWrapper<UserRole>().eq("roleId", roleId));
        return true;
    }

    @CacheEvict(value = "menuCache", allEntries = true)
    @Override
    public boolean batchRemove(String[] ids) throws BusinessException {
        for (String id : ids) {
            if (userRoleDao.selectList(new EntityWrapper<UserRole>().eq("roleId", id)).size() > 0) {
                throw new BusinessException("该角色有绑定用户，请先解绑");
            }
        }
        return deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 设置当前用户的角色checkbox
     *
     * @param uid 当前用户id
     */
    @Override
    public List<RoleChecked> listCheckedRolesByUid(String uid) {
        List<Role> list = selectList(new EntityWrapper<Role>().eq("roleState", 1));
        Set<String> sets = userRoleService.findRolesByUid(uid);
        List<RoleChecked> list2 = list.stream().map(role -> {
            RoleChecked checked = new RoleChecked();
            BeanUtils.copyProperties(role, checked);
            sets.forEach(item -> {
                if (item.equals(checked.getId())) {
                    checked.setChecked(true);
                }
            });
            return checked;
        }).collect(Collectors.toList());
        return list2;
    }

}
