/**
 * 项目名称：mvc.core
 * 文件包名：com.dup.server.services.platform
 * 文件名称：DemoService.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月6日 上午11:33:10
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.server.services.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dup.server.actions.platform.bean.CinemasQuery;
import com.dup.server.actions.platform.bean.CinemasReply;
import com.dup.server.daos.entities.Cinema;
import com.dup.server.services.DemoService;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：DemoService.java
 * @author ly
 */
@Service
public class DemoFacade extends APIFacade {
    
    @Autowired
    private DemoService demoService;
    
    public CinemasReply queryCinemas(CinemasQuery query)
    {
	List<Cinema> list = demoService.queryCinemas(new Cinema());
	
	return new CinemasReply(list, "http://www.baidu.com");
    }
}
