package cn.tedu.store.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.vo.CartVO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartSericeTests {

	@Autowired
	private ICartService service;
	
	

	@Test
	public void addToCart(){	
		Integer uid=1;
		Integer pid=10000034;
		Integer amount=1;
		String username="管理员1";
		service.addToCart(uid, pid, amount, username);
		System.err.println("OK");	
	}
	@Test
	public void findByUid() {
		Integer uid=1;
		 List<CartVO> cartvo =service.getByVOUid(uid);
		 System.err.println("cartvo: "+cartvo.size());
		 for (CartVO cartVO2 : cartvo) {
			System.err.println(cartVO2);
		}
	
	}
	@Test
	public void addNum() {
		try {
			Integer cid=1;
			Integer uid=1;
			String username="管理员";
			Integer rows =service.addNum(uid, username, cid);
			System.err.println(rows);	
		} catch (ServiceException e) {
			System.err.println(e.getClass().getSimpleName());
			System.err.println(e.getMessage());		
		}
	
	}
	@Test
	public void getVOByCids() {
	    Integer[] cids = { 1,2,3,4,5,6,7,8,9,10,11,12,13 };
	    Integer uid = 1;
		List<CartVO> list = service.getByVOCids(cids,uid);
	    System.err.println("count=" + list.size());
	    for (CartVO item : list) {
	        System.err.println(item);
	    }
	}
	

	
}
