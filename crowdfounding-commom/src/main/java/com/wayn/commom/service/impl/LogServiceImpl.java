package com.wayn.commom.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wayn.commom.dao.LogDao;
import com.wayn.commom.domain.Log;
import com.wayn.commom.enums.Operator;
import com.wayn.commom.service.LogService;
import com.wayn.commom.util.ParameterUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    private static Map<String, String> map;

    static {
        map = new HashMap<>();
        for (Operator operator : Operator.values()) {
            map.put(operator.getCode(), operator.getName());
        }
    }

    @Override
    public Page<Log> listPage(Page<Log> page, Log log) {
        EntityWrapper<Log> wrapper = ParameterUtil.get();
        wrapper.like("userName", log.getUserName());
        wrapper.like("moduleName", log.getModuleName());
        wrapper.like("ip", log.getIp());
        wrapper.eq(StringUtils.isNotEmpty(log.getOperation()), "operation", log.getOperation());
        wrapper.eq(log.getOperState() != null, "operState", log.getOperState());
        wrapper.eq(StringUtils.isNotEmpty(log.getOperation()), "operation", log.getOperation());
        Page<Log> logPage = selectPage(page, wrapper);
        for (Log record : logPage.getRecords()) {
            record.setOperation(map.get(record.getOperation()));
        }
        return logPage;
    }
}
