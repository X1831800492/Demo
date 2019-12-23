package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {
	@Autowired
	private  AddressMapper addressMapper;
	
	@Test
	public void insert() {
		Address address = new Address();
		address.setUid(1);
		address.setName("店老板");
		Integer rows = addressMapper.insert(address);
		System.err.println(rows);
		
	}
	@Test
	public void countByUid() {
		Integer uid =1;
		Integer row = addressMapper.countByUid(uid);
		System.err.println(row);
		
	}
	@Test
	public void findByUid() {
		Integer uid =1;
		List<Address> list = addressMapper.findByUid(uid);
		System.err.println("list: "+ list.size());
		for (Address address : list) {
			System.err.println(address);
		}
		
	}
	@Test
	public void findByAid() {
		Integer aid =11;
		Address list = addressMapper.findByAid(aid);
		System.err.println(list);
	}
	@Test
	public void updateNonDefaultByUid() {	
		Integer uid=1;
		Integer row = addressMapper.updateNonDefaultByUid(uid);
		System.err.println(row);
	}
	@Test
	public void updateDefaultByAid() {	
		Integer aid=4;
		String modifedUser="root";
		Date modifiedTime=new Date();
		Integer row = addressMapper.updateDefaultByAid(aid, modifedUser, modifiedTime);
		System.err.println(row);
	}
	@Test
	public void deleteByAid() {	
		Integer aid=3;
		Integer row = addressMapper.deletByAid(aid);
		System.err.println(row);
	}
	
	@Test
	public void findLastModifiedTime() {	
		Integer uid=1;
		Address row = addressMapper.findLastModifiedTime(uid);
		System.err.println(row);
	}
	
}
