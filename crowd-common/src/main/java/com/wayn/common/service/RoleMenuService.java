package com.wayn.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.common.domain.RoleMenu;

import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface RoleMenuService extends IService<RoleMenu> {

	Set<String> findMenusByUid(String id);

}
