package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
/**
 * 处理添加收货地址接口
 * @author JAVA
 *
 */
public interface IAddressService {

	/**
	 * 增加收货地址
	 * @param uid 用户id
	 * @param username 用户姓名
	 * @param address 收货地址， 创建的第1条收货地址是默认的，其它的都不是默认的！
	 */
	void addnew (Integer uid ,String username ,Address address);
	/**
	 * 获取收货地址
	 * @param uid 登录的用户id
	 * @return 对应的所有收货地址
	 */
	List<Address> getByUid(Integer uid);
	/**
	 *根据aid设置默认收货地址
	 * @param aid 收货地址的id
	 * @param uid 当前登录的用户id
	 * @param username 当前登录的用户名
	 */
	void setDefault(Integer aid,Integer uid,String username);
	/**
	 * 删除收货地址
	 * @param aid 收货地址数据的id
	 * @param uid 当前登录的用户id
	 * @param username 当前登录的用户名
	 */
	void delete(Integer aid ,Integer uid,String username);
}
