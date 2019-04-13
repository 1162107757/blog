package top.hjie.util;

import javax.servlet.http.HttpServletRequest;

import top.hjie.pojo.Admin;

/**
 * @Description 操作redis
 * @author 何杰
 * @date 2018年11月26日
 * @version V1.0
 */
public interface IJedisUtil {

	public void set(String key,String vlaue);
	
	public String get(String key);

	public void close();
	
	public void expire(String key,int time);
	
	public Admin getLoginStatus(HttpServletRequest request);
	
	public void delCookieByName(HttpServletRequest request);
	
}
