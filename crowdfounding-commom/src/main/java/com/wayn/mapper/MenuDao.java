package com.wayn.mapper;

import com.wayn.domain.Menu;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

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
