/**
 * 项目名称：mvc.core
 * 文件包名：com.dup.server.services.platform
 * 文件名称：DemoService.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月6日 上午11:33:10
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dup.base.model.Page;
import com.dup.server.actions.platform.bean.CinemasQuery;
import com.dup.server.actions.platform.bean.CinemasReply;
import com.dup.server.daos.CinemaMapper;
import com.dup.server.daos.entities.Cinema;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：DemoService.java
 * @author ly
 */
@Service
public class DemoService extends AbstractService {

    @Autowired
    private CinemaMapper cinemaMapper;
    
    public List<Cinema> queryCinemas(Cinema query)
    {
	Page<Cinema> page = new Page<Cinema>(200);
	return cinemaMapper.queryList(page);
    }
    
    public List<Cinema> queryCinemasTest(Cinema query)
    {
	return cinemaMapper.queryCinemasTest();
    }
}
