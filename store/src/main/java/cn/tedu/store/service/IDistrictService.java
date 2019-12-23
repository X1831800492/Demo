package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;
/**
 * 获取省/市/区/的业务层接口类
 * @author JAVA
 *
 */
public interface IDistrictService {

	/**
	 * 获取省/市/区/的信息
	 * @param parent 父级单位的行政代号，获取全国所有的省/市/区
	 * @return
	 */
	List<District> getByparent(String parent);
	
	/**
	 * 根据省/市/区获取对应的名称
	 * @param code 行政编号
	 * @return 返回对应的省/市/区的名称
	 */
	String getNameByCode(String code);
	
}
