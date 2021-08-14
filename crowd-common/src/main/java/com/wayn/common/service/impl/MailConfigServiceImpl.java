package com.wayn.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.common.dao.MailConfigDao;
import com.wayn.common.domain.MailConfig;
import com.wayn.common.service.MailConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MailConfigServiceImpl extends ServiceImpl<MailConfigDao, MailConfig> implements MailConfigService {


    @Override
    public boolean checkMailConfig(MailConfig mailConfig) {
        if (StringUtils.isBlank(mailConfig.getFromUser())
                || StringUtils.isBlank(mailConfig.getHost())
                || StringUtils.isBlank(mailConfig.getPass())) {
            return false;
        }
        return true;
    }
}
