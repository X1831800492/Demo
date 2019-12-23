package cn.tedu.store.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
/**
 * 实现添加地址的业务层接口类
 * @author JAVA
 *
 */
@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressMapper  addressMapper;
	@Autowired
	private IDistrictService districtService;

	@Value("${project.address.Max-Count}")
	private Integer MaxCount;
	@Override
	public void addnew(Integer uid, String username, Address address) {
		//
		Integer count= countByUid(uid);
		// 判断数量是否达到上限
		if(count>=MaxCount) {
			// 是：抛出AddressCountLimitException
			throw new AddressCountLimitException("收货地址数量不允许超过"+MaxCount+"条!");
		}
		// 补全数据：uid
		address.setUid(uid);
		// 补全数据：is_default(根据以上统计的数量判断得到)
		Integer isDefault = count ==0 ? 1 : 0;
		address.setIsDefault(isDefault);
		//补全数据： 省，市，区的名称
		String provinceName = districtService.getNameByCode(address.getProvinceCode());
		String  cityName= districtService.getNameByCode(address.getCityCode());
		String  areaName = districtService.getNameByCode(address.getAreaCode());
		address.setProvinceName(provinceName);
		address.setCityName(cityName);
		address.setAreaName(areaName);
		// 补全数据：4项日志
		address.setCreatedUser(username);
		address.setCreatedTime(new Date());
		address.setModifiedUser(username);
		address.setModifiedTime(new Date());
		// 执行插入数据
		insert(address);
	}
	@Override
	public List<Address> getByUid(Integer uid) {
		// 调用持久层对象查询到列表
		List<Address> list = findByUid(uid);
		// 遍历列表
		for (Address address : list) {
			// -- 将不需要响应给客户端的属性设置为null：uid/省市区的代号/4项日志
			address.setUid(null);
			address.setProvinceCode(null);
			address.setCityCode(null);
			address.setAreaCode(null);
			address.setModifiedTime(null);
			address.setModifiedUser(null);
			address.setCreatedTime(null);
			address.setCreatedUser(null);			
		}

		// 返回列表
		return list;
	}
	@Transactional
	@Override
	public void setDefault(Integer aid,Integer uid,String username) {
		//调用addressMapper的findByAid()方法,根据参数aid查询收货地址
		Address result = findByAid(aid);
		//判断查询结果是否为null
		if(result==null) {
			//是:抛出AddressNotFounException
			throw new AddressNotFoundException("该地址不存在，或已删除，请刷新后再试");
		}
		//判断查询结果的uid与参数uid是否不匹配(必须使用equals方法)
		if(!result.getUid().equals(uid)) {
			//是：抛出AddressDeniedException
			throw new AccessDeniedException("非法访问");
		}

		// 调用updateNonDefaultByUid()方法，将该用户的所有收货地址都设置为非默认，并获取受影响的行数
		updateNonDefaultByUid(uid);
		
		updateDefaultByAid(aid,  username, new Date());

	}
	@Override
	@Transactional
	public void delete(Integer aid, Integer uid, String username) {
		// 调用findByAid()方法，根据参数aid查询收货地址数据
		Address result = findByAid(aid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：抛出AddressNotFoundException
			throw new AddressNotFoundException(
				"尝试访问的收货地址数据不存在");
		}

		// 判断查询结果中的uid与参数uid是否不匹配(必须使用equals方法对比)
		if (!result.getUid().equals(uid)) {
			// 是：抛出AccessDeniedException
			throw new AccessDeniedException(
				"非法访问");
		}

		// 调用deleteByAid(Integer aid)方法执行删除，并获取返回的受影响行数
		deleteByAid(aid);

		// 判断查询结果中的isDefault是否不为1(为0)
		if (result.getIsDefault()!= 1) {
			// 是：return;
			return;
		}

		// 调用countByUid(Integer uid)方法统计此时该用户的收货地址数据的数量
		Integer count = countByUid(uid);
		// 判断数量是否为0
		if (count == 0) {
			// 是：return;
			return;
		}

		// ----------------- 以下代码不实现 -----------------
		// 判断收货地址数据的数量是否为1
		// 调用addressMapper的“将某用户的所有收货地址全部设置为默认”(该功能尚不存在，需要在持久层中补全)，并获取返回的受影响行数
		// 判断受影响行数是否不为1
		// 是：UpdateException
		// 否：return;
		// -------------------------------------------------

		// 调用findLastModified(Integer uid)找出最后修改的收货地址
		Address lastModified = findLastModifiedTime(uid);
		// 从以上查询结果中获取最后修改的收货地址的id
		Integer lastModifiedAid = lastModified.getAid();
		
		// 执行设置默认收货地址
		updateDefaultByAid(lastModifiedAid, username, new Date());
	}
	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 */
	private void insert(Address address) {
		// 调用addressMapper的insert()方法，插入收货地址数据，并获取返回的受影响行数
		Integer rows = addressMapper.insert(address);
		// 判断受影响的行数是否不为1
		if (rows != 1) {
			// 是：抛出InsertException
			throw new InsertException(
				"插入收货地址数据时出现未知错误，请联系系统管理员");
		}
	}
	
	/**
	 * 根据收货地址数据的id删除数据
	 * @param aid 收货地址数据的id
	 */
	private void deleteByAid(Integer aid) {
		Integer rows = addressMapper.deletByAid(aid);
		if (rows != 1) {
			throw new DeleteException(
				"删除收货地址数据时出现未知错误，请联系系统管理员");
		}
	}
	
	/**
	 * 将指定的收货地址设置为默认
	 * @param aid 收货地址数据的id
	 * @param modifiedUser 修改执行人
	 * @param modifiedTime 修改时间
	 */
	private void updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime) {
		// 执行设置默认收货地址
		Integer rows = addressMapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
		// 判断受影响行数是否不为1
		if (rows != 1) {
			// 是：抛出UpdateException
			throw new UpdateException(
				"更新收货地址数据时出现未知错误，请联系系统管理员");
		}
	}
	
	/**
	 * 将某用户的所有收货地址全部设置为非默认
	 * @param uid 当前登录的用户的id
	 */
	private void updateNonDefaultByUid(Integer uid) {
		Integer rows = addressMapper.updateNonDefaultByUid(uid);
		if (rows < 1) {
			throw new UpdateException("更新收货地址数据时出现未知错误，请联系系统管理员");
		}
	}
	
	/**
	 * 根据收货地址id查询详情
	 * @param aid 收货地址数据的id
	 * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
	 */
	private Integer countByUid(Integer uid) {
		return addressMapper.countByUid(uid);
	}

	/**
	 * 根据收货地址id查询详情
	 * @param aid 收货地址数据的id
	 * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
	 */
	private Address findByAid(Integer aid) {
		return addressMapper.findByAid(aid);
	}
	
	/**
	 * 查询某用户最后修改的收货地址数据
	 * @param uid 用户的id
	 * @return 该用户最后修改的收货地址数据
	 */
	private Address findLastModifiedTime(Integer uid) {
		return addressMapper.findLastModifiedTime(uid);
	}
	
	/**
	 * 获取某用户的收货地址列表
	 * @param uid 用户的id
	 * @return 该用户的收货地址列表
	 */
	private List<Address> findByUid(Integer uid) {
		return addressMapper.findByUid(uid);
	}

}
