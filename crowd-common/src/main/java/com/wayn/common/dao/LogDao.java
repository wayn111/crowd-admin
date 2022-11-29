package com.wayn.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayn.common.domain.OperLog;
import com.wayn.common.domain.vo.EchartVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 日志表 Mapper 接口
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface LogDao extends BaseMapper<OperLog> {

    List<EchartVO> selectModuleUseStatistic();

    List<OperLog> selectLogList(Date begin, Date end);
}
