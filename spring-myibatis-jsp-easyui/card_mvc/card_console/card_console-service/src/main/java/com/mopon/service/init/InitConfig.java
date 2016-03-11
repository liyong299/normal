/**
 * 
 */
package com.mopon.service.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.mopon.util.ProjectXml;

/**
 * 
 * <p>Description: 服务器启动时加载 放入缓存,主要读取配置文件xml、properties文件</p>
 * @date 2015年04月14日
 * @author Aaron
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
@Service("initConfig")
@Lazy(false)
public class InitConfig {

	private Logger logger = LoggerFactory.getLogger(InitConfig.class);
	
	@PostConstruct
	public void initMap(){
		logger.info("初始化文件start[InitConfig]......");
		
		//初始化 sysconfig.properties文件
		ProjectXml.innit();
		
		logger.info("初始化文件end[InitConfig]......");
	}
	
}
