package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Product;

/**
 * 处理商品持久层接口
 * @author XJP
 *
 */
public interface ProductMapper {

	/**
	 * 获取新到好货列表
	 * @return 新到好货列表
	 */
	List<Product> findNewList();
	
	/**
	 * 根据商品id查询商品详细信息
	 * @param id 商品id
	 * @return 商品详细信息
	 */
	Product findById(Integer id);
}
