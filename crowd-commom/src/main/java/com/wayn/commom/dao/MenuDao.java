package com.wayn.commom.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayn.commom.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author wayn
 * @since 2019-04-13
 */
public interface MenuDao extends BaseMapper<Menu> {

	List<String> selectMenuIdsByUid(@Param("uid") String id);

	List<String> selectResourceByUid(@Param("uid") String id);
}
