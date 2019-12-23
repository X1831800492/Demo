package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Product;

/**
 * 商品业务层接口
 * @author JAVA
 *
 */
public interface IProductService {

	/**
	 * 获取新到好货列表
	 * @return 新到好货列表
	 */
	List<Product> getNewList();

	/**
	 * 根据商品id查询商品详细信息
	 * @param id 商品id
	 * @return 商品详细信息
	 */
	Product getById(Integer id);

}
