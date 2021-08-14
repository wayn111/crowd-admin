package com.wayn.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wayn.common.domain.MailConfig;

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
