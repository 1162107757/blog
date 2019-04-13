package top.hjie.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import top.hjie.pojo.AreaDistrict;

@Repository
public interface AreaDistrictMapper extends Mapper<AreaDistrict> {

	List<AreaDistrict> getAreaDistrictByIdList(List<Integer> areas);

	String getAreaNameById(Integer districtId);
	
}
