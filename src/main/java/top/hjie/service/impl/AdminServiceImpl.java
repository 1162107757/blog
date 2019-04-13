package top.hjie.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import top.hjie.mapper.AdminMapper;
import top.hjie.pojo.Admin;
import top.hjie.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

	@Resource
	private AdminMapper adminMapper;
	
	@Override
	public Admin getAdminByAdminCode(String userCode) {
		Example example = new Example(Admin.class);
		example.createCriteria().andEqualTo("adminCode",userCode);
		List<Admin> users = adminMapper.selectByExample(example);
		if(users.size() > 0){
			return users.get(0);
		}else{
			return null;
		}
	}

	
}
