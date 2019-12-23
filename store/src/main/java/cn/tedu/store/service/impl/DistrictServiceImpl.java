package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;
/**
 * 获取省/市/区/的业务层接口实现类
 * @author JAVA
 *
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
	
	
	@Autowired
	private DistrictMapper districtMapper;
	

	@Override
	public String getNameByCode(String code) {
		
		return districtMapper.findNameByCode(code);
	}
	

	@Override
	public List<District> getByparent(String parent) {
		 List<District> list= districtMapper.findByParent(parent);
		 for (District district : list) {
			 district.setId(null);
			 district.setParent(null);
		}
		
		return list;
	}


}
