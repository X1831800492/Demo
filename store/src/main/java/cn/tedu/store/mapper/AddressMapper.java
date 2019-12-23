package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;
/**
 * 处理收货地址数据的持久层接口
 * @author XJP
 *
 */
public interface AddressMapper {
	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响条数
	 */
	Integer insert(Address address);
	
	/**
	 * 统计某用户的收货地址数据的数量
	 * @param aid 收货地址id
	 * @return 该用户地址的数量
	 */
	Integer countByUid(Integer aid);
	/**
	 * 查询用户的收货地址
	 * @param uid 登录用户的uid
	 * @return 用户的收货地址
	 */
	List<Address> findByUid(Integer uid);
	
	
	/**
	 * 将指定的收货地址设置为默认
	 * @param aid 收货地址id
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return 返回受影响的行数
	 */
	Integer updateDefaultByAid(@Param("aid")Integer aid,
							   @Param("modifiedUser")String modifiedUser,
							   @Param("modifiedTime")Date modifiedTime);
		
	

	/**
	 * 将某用户的所有收货地址全部设置为非默认
	 * @param uid  登录用户的uid
	 * @return 返回收货地址全部设置为非默认行数
	 */
	Integer updateNonDefaultByUid(Integer uid);
	
	
	/**
	 * 根据收货地址id查询详情
	 * @param aid 收货地址id
	 * @return 返回收货地址的信息，检查数据是否存在，不存在则返回null  
	 */
	
	Address findByAid(Integer aid);
	
	/**
	 * 根据收货地址id删除
	 * @param aid 收货地址的id
	 * @return
	 */
	Integer deletByAid(Integer aid);
	
	/**
	 * 查询最后修改的收货地址，
	 * @param uid 用户登录的id
	 * @return 返回最后修改的收货地址的信息
	 */
	Address findLastModifiedTime(Integer uid);
	
	
}
