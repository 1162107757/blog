package top.hjie.util;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.hjie.pojo.Admin;

/**
 * @Description redis工具
 * @author 何杰
 * @date 2018年11月27日
 * @version V1.0
 */
public class JedisUtilImpl implements IJedisUtil {

	@Resource
	private JedisPool jedisPool;
	
	public void set(String key,String vlaue){
		try {
			jedisPool.getResource().set(key, vlaue);
		} catch (Exception e) {
		}finally {
			close();
		}
	}
	
	public String get(String key){
		try {
			String value = jedisPool.getResource().get(key);
			return value;
		} catch (Exception e) {
			return null;
		}finally {
			close();
		}
	}
	
	public void del(String key){
		try {
			Jedis jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			
		}finally {
			close();
		}
	}
	
	public void expire(String key,int time){
		try {
			Jedis jedis = jedisPool.getResource();
			jedis.expire(key, time);
		} catch (Exception e) {
		}finally {
			close();
		}
	}

	public void close(){
		try {
			Jedis jedis = jedisPool.getResource();
			jedis.close();
		} catch (Exception e) {
		}
	}
	
	public Admin getLoginStatus(HttpServletRequest request){
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("jsid")){
					return JSON.parseObject(jedisPool.getResource().get(cookie.getValue()), Admin.class);
				}
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public void delCookieByName(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("jsid")){
				del(cookie.getValue());
			}
		}
	}
}
