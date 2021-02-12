package com.wayn.commom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wayn.commom.dao.MailConfigDao;
import com.wayn.commom.domain.MailConfig;
import com.wayn.commom.service.MailConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MailConfigServiceImpl extends ServiceImpl<MailConfigDao, MailConfig> implements MailConfigService {


    @Override
    public boolean checkMailConfig(MailConfig mailConfig) {
        if (StringUtils.isEmpty(mailConfig.getFromUser())
                || StringUtils.isEmpty(mailConfig.getHost())
                || StringUtils.isEmpty(mailConfig.getPass())) {
            return false;
        }
        return true;
    }
}
