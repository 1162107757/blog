package top.hjie.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import top.hjie.pojo.Admin;
import top.hjie.service.IAdminService;
import top.hjie.service.IUserService;
import top.hjie.util.IJedisUtil;
import top.hjie.util.MD5Util;

@Controller
public class IndexController {

	@Resource
	private IUserService userService;
	
	@Resource
	private IAdminService adminService;
	
	@Resource
	private IJedisUtil jedisUtil;
	
	/**
	 * @Description 页面跳转
	 * @author 何杰
	 * @date 2019年3月20日
	 * @param loginCode
	 * @param pageCode
	 * @param request
	 * @return
	 */
	@RequestMapping("index")
	public String index(String loginCode,@RequestParam(name = "pageCode",defaultValue="index")String pageCode,
			HttpServletRequest request){
		if("admin".equals(loginCode)){
			Admin admin = (Admin)request.getSession().getAttribute("Admin");
			if(admin != null){
				return "admin/" + pageCode + ".html";
			}else{
				return "login.jsp";
			}
		}else if("user".equals(loginCode)){
			
		}
		return "login.jsp";
	}
	
	/**
	 * 
	 * @Description 前台登录
	 * @author 何杰
	 * @date 2018年7月28日
	 */
	@RequestMapping("/login")
	public ResponseEntity<?> login(String userCode,String password,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		// 获取当前的Subject
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userCode, MD5Util.getMD5(password),"User");
		token.setRememberMe(true);
		try {
			subject.login(token);
			map.put("success", true);
			map.put("msg", "登陆成功！");
			return ResponseEntity.ok(map);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "登陆失败，用户名或密码错误！");
			return ResponseEntity.ok(map);
		} catch (LockedAccountException e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "登陆失败，账号已停用！");
			return ResponseEntity.ok(map);
		}
	}
	
	/**
	 * @Description 后台登录  
	 * @author 何杰
	 * @date 2018年9月17日
	 */
	@RequestMapping("/admin")
	public ResponseEntity<?> admin(String userCode,String password,HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		// 获取当前的Subject
		Subject subject = SecurityUtils.getSubject();
		Admin admin = jedisUtil.getLoginStatus(request);
		if(admin != null){
			UsernamePasswordToken token = new UsernamePasswordToken(admin.getAdminCode(), admin.getPassword(),"Admin");
			token.setRememberMe(true);
			subject.login(token);
			map.put("success", true);
			map.put("msg", "登陆成功！");
			return ResponseEntity.ok(map);
		}
		if(!subject.isAuthenticated()){
			try {
				UsernamePasswordToken token = new UsernamePasswordToken(userCode, MD5Util.getMD5(password),"Admin");
				token.setRememberMe(true);
				subject.login(token);
				String jsid = UUID.randomUUID().toString();
				Cookie cookie = new Cookie("jsid", jsid);
				cookie.setMaxAge(1000 * 60 * 60 * 24 * 7); // 一周过期
				response.addCookie(cookie);
				jedisUtil.set(jsid, JSON.toJSONString(SecurityUtils.getSubject().getSession().getAttribute("Admin")));
				jedisUtil.expire(jsid, 1000 * 60 * 60 * 24 * 7);
				map.put("success", true);
				map.put("msg", "登陆成功！");
				return ResponseEntity.ok(map);
			} catch (UnknownAccountException e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("msg", "登陆失败，账号不存在！");
				return ResponseEntity.ok(map);
			}catch (IncorrectCredentialsException e){
				e.printStackTrace();
				map.put("success", false);
				map.put("msg", "登陆失败，账号密码错误！");
				return ResponseEntity.ok(map);
			}
		}else{
			Admin adminDB = adminService.getAdminByAdminCode(userCode);
			if(adminDB != null){
				if(adminDB.getPassword().equals(MD5Util.getMD5(password))){
					map.put("success", true);
					map.put("msg", "登陆成功！");
					return ResponseEntity.ok(map);
				}else{
					map.put("success", false);
					map.put("msg", "登陆失败，密码错误！");
					return ResponseEntity.ok(map);
				}
			}else{
				map.put("success", false);
				map.put("msg", "登陆失败，账号不存在！");
				return ResponseEntity.ok(map);
			}
		}
	}
	
	/**
	 * @Description 退出登录
	 * @author 何杰
	 * @date 2018年9月17日
	 */
	@RequestMapping("/loginout")
	public ResponseEntity<?> loginout(String loginCode,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		if("admin".equals(loginCode)){
			request.getSession().removeAttribute("Admin");
			jedisUtil.delCookieByName(request);
		}else if("user".equals(loginCode)){
			request.getSession().removeAttribute("User");
		}else{
			map.put("success", false);
			map.put("msg", "系统错误，退出失败！");
			return ResponseEntity.ok(map);
		}
		// 退出
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		map.put("success", true);
		map.put("msg", "退出成功！");
		return ResponseEntity.ok(map);
	}
	/**
	 * @Description 得到当前登录用户信息  
	 * @author 何杰
	 * @date 2018年9月17日
	 */
	@RequestMapping("/getAdminInfo")
	public void getAdminInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// 得到用户
		Admin admin = (Admin)request.getSession().getAttribute("Admin");
		admin.setPassword(null);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(admin));
		out.flush();
		out.close();
	}
	
}
