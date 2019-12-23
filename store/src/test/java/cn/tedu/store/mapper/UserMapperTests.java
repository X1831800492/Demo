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
public class UserMapperTests {
	@Autowired
	UserMapper mapper;
	@Test
	public void  insert() {
		User user = new User();
		user.setUsername("root");
		user.setPassword("1234");
	
		System.err.println(user);//id=null
		Integer inte = mapper.insert(user);
		System.err.println(inte);
	}
	@Test
	public void updatePasswordByUid() {
		String password="1111";
		Integer uid=1;
		User user=mapper.findByUid(uid);
		String modifiedUser=user.getUsername();
		Date modifiedTime=new Date();
		Integer row= mapper.updatePasswordByUid(uid, password, modifiedUser, modifiedTime);
		System.err.println(row);

	}
	
	@Test
	public void updateAvatarByUid() {
		String avatar="pp11";
		Integer uid=2;
		User user=mapper.findByUid(uid);
		String modifiedUser=user.getUsername();
		Date modifiedTime=new Date();
		Integer row= mapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
		System.err.println(row);

	}
	
	@Test
	public void  updateInfoByUid() {
		User user = new User();
		user.setUid(2);
		user.setPhone("10086");
		user.setGender(0);
		user.setModifiedUser("test");
		user.setModifiedTime(new Date());
		user.setEmail("999@qq.cn");
		Integer row  = mapper.updateInfoByUid(user);
		System.err.println(row);
	}
	
	@Test
	public void findByUid() {
		Integer uid=2;
		User user=mapper.findByUid(uid);
		System.err.println(user);
	}
	@Test
	public void findByUsername() {
		String username="root1";
		User integ=mapper.findByUsername(username);
		System.err.println(integ);	
	}
	
	
	

}
