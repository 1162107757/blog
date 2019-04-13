package top.hjie.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import top.hjie.pojo.Admin;
import top.hjie.pojo.User;
import top.hjie.service.IAdminService;
import top.hjie.service.IUserService;
import top.hjie.util.IJedisUtil;

public class Realm extends AuthorizingRealm {

	@Resource
	private IUserService userService;
	
	@Resource
	private IAdminService adminService;
	
	@Resource
	private IJedisUtil jedisUtil;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1、把AuthenticationToken转换为UsernamePasswordToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		// 2、从UsernamePasswordToken中获取username
        String userCode = usernamePasswordToken.getUsername();
        // 登录类型
        String loginType = usernamePasswordToken.getHost();
        if(loginType.equals("User")){
        	User user = userService.getUserByUserCode(userCode);
        	// 判断用户是否存在
        	if(user == null){
        		throw new UnknownAccountException("账号不存在！");
        	}
        	// 判断账号是否可用
        	if(!user.getStatus()){
        		throw new LockedAccountException("账号已停用");
        	}
        	// 密码匹配
        	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),getName());
        	SecurityUtils.getSubject().getSession().setAttribute("User", user);
        	return info;
        }else{
        	Admin admin = adminService.getAdminByAdminCode(userCode);
        	// 判断用户是否存在
        	if(admin == null){
        		throw new UnknownAccountException("账号不存在！");
        	}
        	// 密码匹配
        	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin.getAdminCode(), admin.getPassword(),getName());
        	SecurityUtils.getSubject().getSession().setAttribute("Admin", admin);
        	return info;
        }
	}

} 
