package cn.tedu.store;

import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTests {

	
	@Test
	public void contextLoads() {
		
	} 
	@Autowired
	DataSource datasource;
	@Test
	public void getConnection() throws SQLException {
		System.err.println(datasource.getConnection());
	}
	@Test 
	public void md5() {
		String password="123489";
		String salt=UUID.randomUUID().toString();
		System.out.println(salt);
		 password=DigestUtils.md5DigestAsHex((salt+password+salt+salt).getBytes());
		System.err.println(password);
	}
	
/*	@Test
	public void commonsCodec() {
		String password = "123456";
		// ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413
		password = DigestUtils.sha512Hex(password);
		System.err.println(password);
	}
	*/
	@Test
	public void uuid() {
		for (int i = 0; i <10; i++) {
			String salt=UUID.randomUUID().toString();
			System.err.println(salt);
		}
			
	}
}
