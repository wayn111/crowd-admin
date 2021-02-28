package com.wayn.commom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayn.commom.domain.Logininfor;
import com.wayn.commom.domain.vo.CityCountVO;

import java.util.List;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface LogininforDao extends BaseMapper<Logininfor> {

    List<CityCountVO> selectLoginLocationCount();
}
