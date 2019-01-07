package cn.tedu.store.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	private IUserService userservice;
	
	@Test
	public void reg() {	
	    try {
			Date now = new Date();
			User user = new User();
			user.setUsername("qwq");
			user.setPassword("123456");
			user.setGender(1);
			user.setPhone("123456789");
			user.setEmail("qq.com");
			User rows = userservice.reg(user);
			System.err.println("rows=" + rows);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void login() {	
	    try {
			String username = "qwqwqq";
			String password = "123456";
			User rows = userservice.login(username,password);
			System.err.println("rows=" + rows);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void changePassword() {	
	    try {
			userservice.changePassword(2, "121212", "123456");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void updateInfo() {	
	    try {
	    	User user = new User();
	    	user.setId(2);
	    	user.setGender(1);
			user.setPhone("123456789");
			user.setEmail("qq.com");
			user.setModifiedUser("杨");
			user.setModifiedTime(new Date());
			userservice.changeInfo(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void changeAvatar() {	
	    try {
			userservice.changeAvatar(8, "12");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
