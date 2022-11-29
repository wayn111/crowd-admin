package com.wayn;

import com.wayn.common.dao.LogDao;
import com.wayn.common.dao.RoleMenuDao;
import com.wayn.common.domain.Menu;
import com.wayn.common.domain.OperLog;
import com.wayn.common.domain.User;
import com.wayn.common.service.LogininforService;
import com.wayn.common.service.UserService;
import com.wayn.common.shiro.util.ShiroUtil;
import com.wayn.common.util.DateUtils;
import kotlin.jvm.Throws;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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

    @Autowired
    private LogDao logDao;

    @Test
    public void test3() throws ParseException {
        Date src = DateUtils.parseDate("2022-11-24 15:40:02");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse(simpleDateFormat.format(src));
        List<OperLog> list = logDao.selectLogList(parse, DateUtils.getLastSecondOfOneDay(src));
        Assert.notEmpty(list, "list can not be empty");
    }

    @Test
    public void test4() throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100000));
        Future<Integer> submit = threadPoolExecutor.submit(() -> {
            try {
                int i = 1 / 0;
                return i;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return null;
            }
        });
        Integer integer = submit.get();
    }

    @Test
    @SneakyThrows
    public void test5() {
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler((t, e) -> {
                log.error(e.getMessage(), e);
            });
            return thread;
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100000),
                threadFactory);
        Future<?> submit = threadPoolExecutor.submit(() -> {
            log.info("---------------------");
            int i = 1 / 0;
        });
        try {
            submit.get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
