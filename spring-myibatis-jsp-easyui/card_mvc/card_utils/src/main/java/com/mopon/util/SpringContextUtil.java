package com.mopon.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * <p>Description:获取spring容器，访问容器中定义的其他bean  </p>
 * @date 2014年3月20日
 * @author tongbiao
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class SpringContextUtil implements ApplicationContextAware {

	// Spring应用上下文环境
	private static ApplicationContext applicationContext;

	/**  
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境  
	 *   
	 * @param applicationContext  
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtil.applicationContext = applicationContext;
	}

	/**  
	 * @return ApplicationContext  
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**  
	 * 获取对象  
	 * 这里重写了bean方法，起主要作用  
	 * @param name  
	 * @return Object 一个以所给名字注册的bean的实例  
	 * @throws BeansException  
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

}