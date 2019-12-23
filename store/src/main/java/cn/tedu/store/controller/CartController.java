package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.JsonResult;
import cn.tedu.store.vo.CartVO;
/**
 * 处理购物车相关请求的控制器
 * @author JAVA
 *
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController {

	@Autowired
	private ICartService cartService;
	
	@RequestMapping("add_to_cart")
	public JsonResult<Void> addToCart(Integer pid, Integer amount,HttpSession session){		
		Integer uid=getuidFromSession(session);
		String username=getUsernameFromSession(session);
		cartService.addToCart(uid, pid, amount, username);
		return new JsonResult<>(OK);
	}
	@RequestMapping({"","/"})
	public JsonResult<List<CartVO>> getVOByUid(HttpSession session){		
		Integer uid=getuidFromSession(session);
		List<CartVO>  data=  cartService.getByVOUid(uid);
		return new JsonResult<>(OK,data);
	}
	@RequestMapping("{cid}/num/add")
	public JsonResult<Integer> addNum(HttpSession session,@PathVariable("cid")Integer cid){		
		Integer uid=getuidFromSession(session);
		String username=getUsernameFromSession(session);
		Integer data=  cartService.addNum(uid, username, cid);
		return new JsonResult<>(OK,data);
	}
	
	@RequestMapping("get_by_cids")
	public JsonResult<List<CartVO>> getVOByCids(HttpSession session,Integer[] cids){		
		Integer uid=getuidFromSession(session);
		List<CartVO> data=  cartService.getByVOCids(cids, uid);
		return new JsonResult<>(OK,data);
	}
	
}
