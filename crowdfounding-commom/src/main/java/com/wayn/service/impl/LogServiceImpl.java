package com.wayn.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wayn.domain.Log;
import com.wayn.mapper.LogDao;
import com.wayn.service.LogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, Log> implements LogService {

    @Override
    public Page<Log> listPage(Page<Log> page, Log log) {
        EntityWrapper<Log> wrapper = new EntityWrapper<>();
        wrapper.like("userName", log.getUserName());
        wrapper.eq(StringUtils.isNotEmpty(log.getOperation()), "operation", log.getOperation());
        return selectPage(page, wrapper);
    }
}
