package com.wayn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wayn.commom.domain.Menu;
import com.wayn.commom.dao.RoleMenuDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-*.xml" })
@WebAppConfiguration
public class MenuTest {

	@Autowired
	private RoleMenuDao roleMenDao;

	@Test
	public void test() {
		List<Menu> list = selectTreeMenuByUserId("c79ba431f9f74dfbae585b87b0cde933");
		list = selectTreeMenuByMenusAndPid(list, 0L);
		list.forEach(data -> {
			System.out.println(data);
			if (data.getChildren() != null) {
				data.getChildren().forEach(data1 -> {
					System.out.println("\t" + data1.toString());
				});

			}
		});
	}

	public List<Menu> selectTreeMenuByUserId(String id) {
		List<Menu> menus = roleMenDao.selectRoleMenuIdsByUserId(id);
		return menus;
	}

	public List<Menu> selectTreeMenuByMenusAndPid(List<Menu> menus, Long pid) {
		List<Menu> returnList = new ArrayList<Menu>();
		menus.forEach(menu -> {
			if (pid.equals(menu.getPid())) {
				if (menu.getType() < 2) {
					menu.setChildren(selectTreeMenuByMenusAndPid(menus, menu.getId()));
				}
				returnList.add(menu);
			}

		});
		return returnList;
	}
}
