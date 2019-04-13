package top.hjie.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import top.hjie.mapper.AreaDistrictMapper;
import top.hjie.mapper.UserMapper;
import top.hjie.pojo.User;
import top.hjie.pojo.info.UserSerachInfo;
import top.hjie.service.IUserService;
import top.hjie.util.MD5Util;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private AreaDistrictMapper areaDistrictMapper;
	
	@Override
	public PageInfo<User> getAllUser(UserSerachInfo userSerachInfo,Integer page,Integer limit) {
		PageHelper.startPage(page,limit);
		List<User> users = userMapper.getAllUser(userSerachInfo);
		for (User user : users) {
			user.setPassword(null);
			user.setCountryName(areaDistrictMapper.getAreaNameById(user.getCountry()));
			user.setProvinceName(areaDistrictMapper.getAreaNameById(user.getProvince()));
			user.setCityName(areaDistrictMapper.getAreaNameById(user.getCity()));
			user.setAreaName(areaDistrictMapper.getAreaNameById(user.getArea()));
		}
		PageInfo<User> info = new PageInfo<>(users);
		return info;
	}

	@Override
	public void addUser(User user) {
		userMapper.insertSelective(user);
	}

	@Override
	public User getUser(String userName, String password) {
		User user = userMapper.getUser(userName,password);
		return user;
	}

	@Override
	public User getUserByUserCode(String userCode) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("userCode",userCode);
		List<User> users = userMapper.selectByExample(example);
		if(users.size() > 0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public void delUser(String userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void resetPassWord(String userId) {
		User user = new User();
		user.setUserId(userId);
		user.setPassword(MD5Util.getMD5("888888"));
		userMapper.updateByPrimaryKeySelective(user);
	}
	
}
