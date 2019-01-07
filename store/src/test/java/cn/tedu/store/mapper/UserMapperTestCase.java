package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void addnew() {
		Date now = new Date();
		User user = new User();
		user.setUsername("root");
		user.setPassword("123456");
		user.setSalt("dm4");
		user.setGender(1);
		user.setPhone("123456789");
		user.setEmail("qq.com");
		user.setAvater("qwqwq");
		user.setIsDelete(0);
		user.setCreatedUser("qwqw");
	    user.setModifiedUser("erer");
	    user.setCreatedTime(now);
	    user.setModifiedTime(now);
	    Integer rows = userMapper.addnew(user);
	    System.err.println("rows=" + rows);
	}
	@Test
	public void findByUsername() {
		String username = "root";
        User user = userMapper.findByUsername(username);
        System.err.println(user);
	}
	@Test
	public void updatePassword() {
        System.err.println(userMapper.updatePassword(1,"qw","qw",new Date()));
	}
	@Test
	public void updateInfo() {
		Date now = new Date();
		User user = new User();
		user.setId(6);
		user.setGender(1);
		user.setPhone("123456789");
		user.setEmail("qq.com");
	    user.setModifiedUser("erer");
	    user.setModifiedTime(now);
        System.err.println(userMapper.updateInfo(user));
	}
	@Test
	public void updateAvatar() {
		System.out.println("0.0");
		Date now = new Date();
        System.err.println(userMapper.updateAvatar(8, "1212", "杨", now));
	}
}
