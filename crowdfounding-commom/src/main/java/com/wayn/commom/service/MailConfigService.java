package com.wayn.commom.service;

import com.baomidou.mybatisplus.service.IService;
import com.wayn.commom.domain.MailConfig;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface MailConfigService extends IService<MailConfig> {

    boolean checkMailConfig(MailConfig mailConfig);

}
