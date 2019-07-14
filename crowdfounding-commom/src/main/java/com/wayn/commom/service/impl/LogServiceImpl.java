package com.wayn.commom.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.util.ParameterUtil;
import com.wayn.commom.domain.Log;
import com.wayn.commom.dao.LogDao;
import com.wayn.commom.service.LogService;
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
        EntityWrapper<Log> wrapper = ParameterUtil.get();
        wrapper.like("userName", log.getUserName());
        wrapper.eq(StringUtils.isNotEmpty(log.getOperation()), "operation", log.getOperation());
        return selectPage(page, wrapper);
    }
}
