package cn.tedu.store.mapper;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests2 {
	@Autowired
	private  DistrictMapper Mapper;
	
	@Test
	public void findByparent() {
		String  parent = "321200";
		List<District> list = Mapper.findByParent(parent);
		System.err.println("count"+list.size());
		for (District district : list) {
			System.err.println(district);
		}
		
	}
	
	@Test
	public void findNameByCode() {
		String  code = "321200";
		String name = Mapper.findNameByCode(code);
		System.err.println("name: "+name);
	}
		
}
