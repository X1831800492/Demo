package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.ex.ProductNotFoundException;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> getNewList(){
		List<Product> list = findNewList();
		for (Product product : list) {
			product.setPriority(null);
			product.setModifiedTime(null);
			product.setModifiedUser(null);
			product.setCreatedTime(null);
			product.setCreatedUser(null);
			product.setStatus(null);		
		}
		return list;
	}
	
	@Override
	public Product getById(Integer id) {
	Product product=findById(id);
	if(product==null) {
		throw new ProductNotFoundException("尝试访问的商品数据不存在");
	}
	product.setPriority(null);
	product.setModifiedTime(null);
	product.setModifiedUser(null);
	product.setCreatedTime(null);
	product.setCreatedUser(null);
	product.setStatus(null);	
	return product;
	}
	
	
	/**
	 * 获取新到好货列表
	 * @return 新到好货列表
	 */
 	private  List<Product> findNewList(){
 		return productMapper.findNewList();
 	}
 	
 	/**
	 * 根据商品id查询商品详细信息
	 * @param id 商品id
	 * @return 商品详细信息
	 */
	private Product findById(Integer id) {
		return productMapper.findById(id);
	}

	
}
