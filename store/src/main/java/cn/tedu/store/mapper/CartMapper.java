package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

/**
 * 处理购物车持久层接口
 * @author XJP
 *
 */
public interface CartMapper {
	/**
	 * 向购物车中添加商品
	 * @param cart 商品信息
	 * @return 商品信息
	 */
	Integer inster(Cart cart);
	
	/**
	 * 修改购物车中的商品，若存在则加若干，若不存在则新增商品
	 * @param cid 购物车中商品id
	 * @param num 购物车中商品数量
	 * @param modifiedUser 最后修改人
	 * @param modifiedTime 最后修改时间
	 * @return 增加成功受影响条数
	 */
	Integer updateNumByCid(
			@Param("cid")Integer cid,
			@Param("num")Integer num,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 查询购物车中商品是否存在
	 * @param uid 用户登录iD
	 * @param pid 商品的id
	 * @return 若购物车中存在商品则返回信息，若不存在则返回null
	 */
	Cart findByUidAndPid(
			@Param("uid")Integer uid,
			@Param("pid")Integer pid);
	/**
	 * 查询购物车商品信息
	 * @param uid 用户登录iD
	 * @return 购物车商品信息
	 */
	List<CartVO> findByVOUid(Integer uid);
	/**
	 * 查询购物车商品是否存在
	 * @param cid 购物车中商品id
	 * @return 购物车中商品信息
	 */
	Cart findByCid(Integer cid);
	/**
	 * 根据多条购物车数据id查询多条购物车数据
	 * @param cids 多个购物车id
	 * @return 多个购物车商品信息
	 */
	List<CartVO> findByVOCids(Integer[] cids);
	
	
}
