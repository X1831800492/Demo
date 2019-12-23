package cn.tedu.store.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressSericeTests {

	@Autowired
	private IAddressService service;
	
	@Test
	public void addnew() {
		try {
			Integer uid = 1;
			String username="店老板";
			Address address=new Address();
			address.setName("小4");
			address.setUid(1);
			address.setModifiedTime(new Date());
			address.setCreatedTime(new Date());
			address.setCreatedUser(username);
			address.setModifiedUser(username);
			service.addnew(uid, username, address);
			System.err.println("OK");
			
		} catch (ServiceException e) {
			System.err.println(e.getClass().getSimpleName());
			System.err.println(e.getMessage());
		}
		
	}

	@Test
	public void  getByUid() {
		
		Integer uid = 1;
		List<Address> list = service.getByUid(uid);
		System.err.println("list: "+list.size());
		for (Address address : list) {
			System.err.println(address);
		}
	
	}
	@Test
	public void setDefault() {
		try {
			Integer uid = 1;
			Integer aid = 4;
			String username="root";
			service .setDefault(aid, uid, username);
			System.err.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getSimpleName());
			System.err.println(e.getMessage());
		}
		
	}
	@Test
	public void delete() {
		try {
			Integer aid = 2;
			Integer uid = 1;
			String username = "管理员";
			service.delete(aid, uid, username);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getSimpleName());
			System.err.println(e.getMessage());
		}
	}

		
	
}
