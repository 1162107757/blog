package top.hjie.controller.view;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import top.hjie.pojo.User;
import top.hjie.pojo.info.UserSerachInfo;
import top.hjie.service.IUserService;
import top.hjie.util.MD5Util;
import top.hjie.util.ObjectSetValueNull;


@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;
	
	/**
	 * @Description 查询所有用户 
	 * @author 何杰
	 * @date 2018年7月25日
	 */
	@RequestMapping(value = "/getAllUser")
	public ResponseEntity<?> getAllUser(@RequestParam(value = "page",defaultValue="1")Integer page,
			@RequestParam(value = "limit",defaultValue="10")Integer limit,
			UserSerachInfo userSerachInfo){
		PageInfo<User> users = userService.getAllUser(userSerachInfo,page,limit);
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		map.put("data", users.getList());
		map.put("count", users.getTotal());
		map.put("code", "0");
		return ResponseEntity.ok(map);
	}
	
	/**
	 * 添加用户
	 * @Description   
	 * @author 何杰
	 * @date 2018年7月25日
	 */
	@RequestMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user){
		Map<String, Object> map = new HashMap<>();
		User userDB = userService.getUserByUserCode(user.getUserCode());
		if(userDB != null){
			map.put("success", false);
			map.put("msg", "会员账号添加失败，用户名已存在！");
			return ResponseEntity.ok(map);
		}
		try {
			// 设置主键id
			user.setUserId(UUID.randomUUID().toString());
			// 加密密码
			user.setPassword(MD5Util.getMD5(user.getPassword()));
			user.setRegisterDate(new Date());
			// 空字符串设置为null
			Object object = ObjectSetValueNull.changeToNull(user);
			userService.addUser((User)object);
			map.put("success", true);
			map.put("msg", "会员账号添加成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "会员账号添加失败！");
			e.printStackTrace();
		}
		return ResponseEntity.ok(map);
	}
	
	/**
	 * @Description 删除用户  
	 * @author 何杰
	 * @date 2018年7月25日
	 */
	@RequestMapping("/delUser")
	public ResponseEntity<?> delUser(String userId,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		try {
			userService.delUser(userId);
			map.put("success", true);
			map.put("msg", "删除成功！");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "系统错误，删除失败！");
			return ResponseEntity.ok(map);
		}
	}
	
	/**
	 * @Description 编辑用户
	 * @author 何杰
	 * @date 2018年7月26日
	 */
	@RequestMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		Map<String, Object> map = new HashMap<>();
		User userDB = userService.getUserByUserCode(user.getUserCode());
		if(userDB == null){
			map.put("success", false);
			map.put("msg", "用户不存在！");
			return ResponseEntity.ok(map);
		}
		Object object = ObjectSetValueNull.changeToNull(user);
		try {
			userService.updateUser((User)object);
			map.put("success", true);
			map.put("msg", "编辑成功！");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "系统错误，编辑失败！");
			return ResponseEntity.ok(map);
		}
	}
	
	/**
	 * @Description 重置用户密码
	 * @author 何杰
	 * @date 2019年1月23日
	 */
	@RequestMapping("/resetPassWord")
	public ResponseEntity<?> resetPassWord(String userId){
		Map<String, Object> map = new HashMap<>();
		try {
			userService.resetPassWord(userId);
			map.put("success", true);
			map.put("msg", "重置成功！");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "系统错误，编辑失败！");
			return ResponseEntity.ok(map);
		}
	}
}
