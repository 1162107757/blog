package top.hjie.service;

import java.util.List;


import top.hjie.pojo.AreaDistrict;

public interface IAreaDistrictService {

	List<AreaDistrict> getAreaDistrictByLevel(Integer level,Integer parentId);

}
