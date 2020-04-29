package com.wayn.commom.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.dao.MailConfigDao;
import com.wayn.commom.domain.MailConfig;
import com.wayn.commom.service.MailConfigService;
import org.springframework.stereotype.Service;

@Service
public class MailConfigServiceImpl extends ServiceImpl<MailConfigDao, MailConfig> implements MailConfigService {
}
