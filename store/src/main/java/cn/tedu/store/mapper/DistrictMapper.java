package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;
/**
 * 处理获取省/市/区/的持久层接口
 * @author JAVA
 *
 */
public interface DistrictMapper {
	/**
	 * 获取全国所有的省/某省所有的市/某市所有的区的数据列表
	 * @param parent 父级单位的行政代号，获取全国所有的省/市/区
	 * @return 全国所有的省/某省所有的市/某市所有的区的数据列表
	 */
	List<District> findByParent(String parent);
	
	/**
	 * 根据省/市/区获取对应的名称
	 * @param Code 行政编号
	 * @return 返回对应的省/市/区的名称
	 */
    String findNameByCode(String Code);
	

}
