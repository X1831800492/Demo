package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductSericeTests {

	@Autowired
	private IProductService service;
	
	

	@Test
	public void  getNewList(){
		
		List<Product> list = service.getNewList();
		System.err.println("list: "+list.size());
		for (Product product : list) {
			System.err.println(product);
		}
	
	}
	
	@Test
	public void  getById(){	
		Integer id=100000021;
		Product product = service.getById(id);
		System.err.println(product);	
	}
	

	
}
