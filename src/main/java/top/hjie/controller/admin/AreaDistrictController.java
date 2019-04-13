package top.hjie.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import top.hjie.pojo.AreaDistrict;
import top.hjie.service.IAreaDistrictService;

@Controller
@RequestMapping("/areaDistrict")
public class AreaDistrictController {

	@Resource
	private IAreaDistrictService areaDistrictService;
	
	/**
	 * 根据层级查出地区
	 */
	@RequestMapping("/getAreaDistrictByLevel")
	public ResponseEntity<?> getAreaDistrictByLevel(Integer level,Integer parentId){
		List<AreaDistrict> areas = areaDistrictService.getAreaDistrictByLevel(level,parentId);
		return ResponseEntity.ok(areas);
	}
	
}
