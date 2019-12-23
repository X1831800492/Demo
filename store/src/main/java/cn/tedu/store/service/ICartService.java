package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.vo.CartVO;

/**
 * 处理购物车业务层的接口
 * @author JAVA
 *
 */

public interface ICartService {
	/**
	 * 向购物车中添加商品
	 * @param uid 登录用户id
	 * @param pid 商品id
	 * @param amount 增加数量
	 * @param username 登录用户名
	 */
	void addToCart(Integer uid,Integer pid,Integer amount,String username);
	
	/**
	 * 查询购物车商品信息
	 * @param uid 用户登录iD
	 * @return 购物车商品信息
	 */
	List<CartVO> getByVOUid(Integer uid);
	/**
	 * 将购物车中商品数量加1
	 * @param uid 用户登录iD
	 * @param username 登录用户名
	 * @param cid 购物车中需要加1的商品的id
	 * @return
	 */
	Integer addNum(Integer uid ,String username ,Integer cid);
	/**
	 * 根据多条购物车数据id查询多条购物车数据
	 * @param cids 多个购物车id
	 * @param uid 用户登录iD
	 * @return 多个购物车商品信息
	 */
	List<CartVO> getByVOCids(Integer[] cids,Integer uid);

}
