package cn.tedu.store.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

	@Autowired
	private IUserService service;
	
	@Test
	public void reg() {
		try {
			User user = new User();
			user.setUsername("Spring6");
			user.setPassword("1234");
			service.reg(user);
			System.err.println("OK");
		} catch (Exception e) {
			System.err.println(e.getClass()); 
		}	
	}
	@Test
	public void Login() {
		try {
			String username="Spring";
			String password="1234";
			User user=service.login(username, password);
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
		
	}
	@Test
	public void updatepassword() {
		try {
			Integer uid= 10;
			String username="aaa";
			String oldPassword="1234";
			String  newPassword="789";
	service.changePassword(uid, username, oldPassword, newPassword);
	System.out.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void updateavatar() {
		try {
			Integer uid= 2;
			String username="test";
			String avatar="7779";			
	service.chengeAvatar(uid, username, avatar);
	System.err.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void updateInfo() {
		try {
			User user = new User();
			Integer uid= 2;
			String username="test";
			user.setPhone("99999");
			user.setEmail("test@163.com");
			user.setGender(1);
			user.setModifiedTime(new Date());
			user.setModifiedUser(username);
	service.chengeInfo(uid, username, user);
	System.err.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void aa() {
		try {
			Integer uid=1;
			User user=service.getinfo(uid);
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
		
	}
	
}
