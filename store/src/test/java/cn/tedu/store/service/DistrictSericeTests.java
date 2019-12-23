package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.tedu.store.entity.District;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictSericeTests {

	@Autowired
	private  IDistrictService districtService;
	
	@Test
	public void getByparent() {
		String  parent = "86";
		List<District> list = districtService.getByparent(parent);
		System.err.println("count"+list.size());
		for (District district : list) {
			System.err.println(district);
		}
		
	}
	
	@Test
	public void findNameByCode() {
		String  code = "321200";
		String name =districtService.getNameByCode(code);
		System.err.println("name: "+name);
	
	}
		
}