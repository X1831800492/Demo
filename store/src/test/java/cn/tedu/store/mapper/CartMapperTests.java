package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
	@Autowired
	private  CartMapper cartMapper;
	
	@Test
	public void insert() {
		Cart cart = new Cart();
		cart.setUid(1);
		cart.setPid(1);
		cart.setNum(5);
		cart.setPrice(100L);
		cart.setCreatedUser("管理员");
		cart.setModifiedUser("管理员");
		cart.setCreatedTime(new Date());
		cart.setModifiedTime(new Date());
		Integer rows = cartMapper.inster(cart);
		System.err.println(rows);		
	}
	@Test
	public void findByUidAndPid() {
		
		Integer uid=1;
		Integer pid=10000037;
		Cart rows =cartMapper.findByUidAndPid(uid, pid);
		System.err.println(rows);
		
	}
	@Test
	public void updateNumByCid() {
		Integer cid=1;
		Integer num=6;
		String modifiedUser="管理员";
		Integer rows =cartMapper.updateNumByCid(cid, num, modifiedUser, new Date());
		System.err.println(rows);
		
	}
	@Test
	public void findByUid() {
		Integer uid=1;
		 List<CartVO> cartvo =cartMapper.findByVOUid(uid);
		 System.err.println("cartvo: "+cartvo.size());
		 for (CartVO cartVO2 : cartvo) {
			System.err.println(cartVO2);
		}
		
		
	}
	@Test
	public void findByCid() {
		Integer cid=1;
		Cart cart =cartMapper.findByCid(cid);	
			System.err.println(cart);
	
	}
	@Test
	public void findByVOCids() {
		
		Integer[] cids= {1,2,3};
		List<CartVO> cart =cartMapper.findByVOCids(cids);	
		System.err.println(cart.size());
		for (CartVO cartVO : cart) {
			System.err.println(cartVO);
		}
		
		
	}
	
}
