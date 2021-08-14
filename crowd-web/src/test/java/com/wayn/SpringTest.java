package com.wayn;

import com.wayn.common.dao.RoleMenuDao;
import com.wayn.common.domain.Menu;
import com.wayn.common.domain.User;
import com.wayn.common.service.LogininforService;
import com.wayn.common.service.UserService;
import com.wayn.common.shiro.util.ShiroUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-*.xml"})
@WebAppConfiguration
public class SpringTest {

    @Autowired
    private RoleMenuDao roleMenDao;

    @Autowired
    private UserService userService;

    @Autowired
    private LogininforService logininforService;

    @Test
    public void test() {
        List<User> entityList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setUserName("admin" + i);
            user.setDeptId(1L);
            user.setPassword(ShiroUtil.md5encrypt("123456", "admin"));
            entityList.add(user);
        }
        userService.saveBatch(entityList);
    }


    @Test
    public void test2() {
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
