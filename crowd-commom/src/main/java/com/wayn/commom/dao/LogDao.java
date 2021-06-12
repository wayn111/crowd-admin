package com.wayn.commom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayn.commom.domain.OperLog;
import com.wayn.commom.domain.vo.EchartVO;

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
}
