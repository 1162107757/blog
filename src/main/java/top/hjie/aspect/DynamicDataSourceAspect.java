package top.hjie.aspect;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import top.hjie.pojo.Admin;


/**
 * @Description 动态切换数据源
 * @author 何杰
 * @date 2019年1月11日
 * @version V1.0
 */
@Aspect
public class DynamicDataSourceAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
	

	//切换放在mapper接口的方法上，所以这里要配置AOP切面的切入点
	@Pointcut("execution( * top.hjie.controller.*.*.*(..))")
    public void dataSourcePointCut() {
    }
	
	@Before("dataSourcePointCut()")
    public void beforeSwitchDS(JoinPoint point) throws Exception{
		Object obj = point.getTarget();
		String name = point.getSignature().getName();
		Class<? extends Object> clazz = obj.getClass();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("Admin");
		if(!name.equals("index") & !name.equals("admin")){
			if(admin == null){
				throw new RuntimeException("无操作权限！");
			}
		}
    }
    
    @After("dataSourcePointCut()")
    public void afterSwitchDS(JoinPoint point){
    	
    }
	
}
