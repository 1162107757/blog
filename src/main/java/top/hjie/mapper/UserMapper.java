package top.hjie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import top.hjie.pojo.User;
import top.hjie.pojo.info.UserSerachInfo;

@Repository
public interface UserMapper extends Mapper<User> {

	User getUser(@Param("userName")String userName, @Param("password")String password);

	List<User> getAllUser(UserSerachInfo userSerachInfo);
	
}
