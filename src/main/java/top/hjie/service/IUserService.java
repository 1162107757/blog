package top.hjie.service;


import com.github.pagehelper.PageInfo;

import top.hjie.pojo.User;
import top.hjie.pojo.info.UserSerachInfo;

public interface IUserService {

	PageInfo<User> getAllUser(UserSerachInfo userSerachInfo, Integer page,Integer limit);

	void addUser(User user);

	User getUser(String userName, String password);

	User getUserByUserCode(String userCode);

	void delUser(String userId);

	void updateUser(User user);

	void resetPassWord(String userId);
	
}
