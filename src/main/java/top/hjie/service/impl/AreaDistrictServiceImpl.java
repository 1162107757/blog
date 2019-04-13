package top.hjie.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import tk.mybatis.mapper.entity.Example;
import top.hjie.mapper.AreaDistrictMapper;
import top.hjie.pojo.AreaDistrict;
import top.hjie.service.IAreaDistrictService;

@Service
public class AreaDistrictServiceImpl implements IAreaDistrictService {

	@Resource
	private AreaDistrictMapper areaDistrictMapper;
	
	/**
	 * 根据层级查询或者根据父级id查询
	 */
	@Override
	public List<AreaDistrict> getAreaDistrictByLevel(Integer level,Integer parentId) {
		if(parentId != null){
			Example example = new Example(AreaDistrict.class);
			example.createCriteria().andEqualTo("parentId",parentId);
			return areaDistrictMapper.selectByExample(example);
		}else{
			Example example = new Example(AreaDistrict.class);
			example.createCriteria().andEqualTo("level",level);
			return areaDistrictMapper.selectByExample(example);
		}
	}
	
}
