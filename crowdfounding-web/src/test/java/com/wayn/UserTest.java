package com.wayn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wayn.domain.User;
import com.wayn.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-*.xml" })
@WebAppConfiguration
public class UserTest {

	@Autowired
	private UserService userService;

	@Test
	public void test() {
		List<User> entityList = new ArrayList<User>();
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			user.setUserName("admin" + i);
			user.setDeptId(1L);
			user.setPassword(new SimpleHash("MD5", "123456", user.getUserName(), 1024).toString());
			entityList.add(user);
		}
		userService.insertBatch(entityList);
	}

}
