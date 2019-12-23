package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;
@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private IProductService productService;

	@Override
	public void addToCart(Integer uid, Integer pid,Integer amount,String username) {
		// 创建当前时间对象now(new Date())
		Date date = new Date();
		// 调用当前业务实现类的私有findByUidAndPid(Integer uid, Integer pid)方法执行查询
		Cart cart=findByUidAndPid(uid,pid);
		// 判断查询结果是否为null
		if(cart==null) {
			// 是：找不到数据，表示该用户的购物车中没有该商品，则需要插入新的记录
			// -- 调用productService的getById(Integer id)方法，根据参数pid查询商品数据，从中得到商品单价
			Product product=productService.getById(pid);
			// -- 创建新的Cart对象
			Cart result = new Cart();
			// -- 补全数据：uid
			result.setUid(uid);
			// -- 补全数据：pid
			result.setPid(pid);
			// -- 补全数据：num(amount)
			result.setNum(amount);
			// -- 补全数据：price
			result.setPrice(product.getPrice());
			// -- 补全数据：4个日志(username, now)
			result.setCreatedTime(date);
			result.setCreatedUser(username);
			result.setModifiedTime(date);
			result.setModifiedUser(username);
			// -- 调用当前业务实现类私有的insert(Cart cart)执行插入数据
			inster(result);
		}else {
		// 否：找到了数据，表示该用户的购物车中已有该商品，则需要修改商品数量
		// -- 从查询结果中获取cid，用于最终调用修改数量的方法
		Integer cid=cart.getCid();
		// -- 从查询结果中取出num，即原有的数量，与参数amount(增量)相加，得到新的数量，用于最终调用修改数量的方法
		Integer num =  (cart.getNum()+amount);
		// -- 调用当前业务实现类私有的updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime)执行修改商品数量
		updateNumByCid(cid, num, username, date);
		}
	}

	@Override
	public List<CartVO> getByVOUid(Integer uid) {
		
		return findByVOUid(uid);
	}

	@Override
	public Integer addNum(Integer uid, String username, Integer cid) {
		//调用cartMapper的findByAid()方法,根据参数cid查询商品是否存在
		Cart result= findByCid(cid);
		//判断查询结果是否为nul
		if(result==null) {
			//是:抛出CartNotFoundException
			throw new CartNotFoundException("商品不存在");
		}
		//判断查询结果的uid与参数uid是否不匹配(必须使用equals方法)
		if(!result.getUid().equals(uid)) {
			//是：抛出AddressDeniedException
			throw new AccessDeniedException("非法访问异常");
		}
		//取出购物车中存在的商品数量加1
		Integer num = result.getNum()+1;
		//设置购物车中能存在的最大数量
		if(num>100) {//（可不写）
			//超出数量抛出UpdateException
			throw new UpdateException("不允许添加超过100件");
		}else {
			// -- 调用当前业务实现类私有的updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime)执行修改商品数量
		updateNumByCid(cid, num, username, new Date());
		}
		//返回更新后的数量
		return num;
		
	}
	@Override
	public List<CartVO> getByVOCids(Integer[] cids,Integer uid) {
		//执行查询数据
		List<CartVO> carts=findByVOCids(cids);
		
		//遍历查询结果，对比uid，并移除不是当前用户的数据
		//迭代器，是一种安全的在遍历过程中移除元素额工具
		Iterator<CartVO> it = carts.iterator();
		while(it.hasNext()) {
			CartVO cart = it.next();
			if(!cart.getUid().equals(uid)) {
				it.remove();
			}
				
		}
		//返回结果
		return carts;
	}

	/**
	 * 根据多条购物车数据id查询多条购物车数据
	 * @param cids 多个购物车id
	 * @return 多个购物车商品信息
	 */
	private	List<CartVO> findByVOCids(Integer[] cids){
		return cartMapper.findByVOCids(cids);
	}


	/**
	 * 查询购物车中需要增加的商品是否存在
	 * @param cid 购物车中商品id
	 * @return 购物车中商品信息
	 */
	private Cart findByCid(Integer cid) {
		return cartMapper.findByCid(cid);
		
	}
	/**
	 * 查询购物车商品信息
	 * @param uid 用户登录iD
	 * @return 购物车商品信息
	 */
	private	List<CartVO> findByVOUid(Integer uid){
		
		return cartMapper.findByVOUid(uid);
	}




	/**
	 * 查询购物车中商品是否存在
	 * @param uid 用户登录iD
	 * @param pid 商品的id
	 * @return 若购物车中存在商品则返回信息，若不存在则返回null
	 */
	private Cart findByUidAndPid(Integer uid,Integer pid) {
		return cartMapper.findByUidAndPid(uid, pid);


	}
	/**
	 * 向购物车中添加商品
	 * @param cart 商品信息
	 * @return 商品信息
	 */
	private	void inster(Cart cart) {
		Integer rows= cartMapper.inster(cart);
		if(rows!=1) {
			throw new UpdateException("添加出现未知错误，请稍后再试");
		}

	}

	/**
	 * 修改购物车中的商品，若存在则加若干，若不存在则新增商品
	 * @param cid 购物车中商品id
	 * @param num 购物车中商品数量
	 * @param modifiedUser 最后修改人
	 * @param modifiedTime 最后修改时间
	 * @return 增加成功受影响条数
	 */
	private void updateNumByCid(Integer cid,Integer num,String modifiedUser,Date modifiedTime) {

		Integer rows =cartMapper.updateNumByCid(cid, num, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("添加出现未知错误，请稍后再试");
		}

	}

	




}
