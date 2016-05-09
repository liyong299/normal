/**
 * 项目名称：mvc.core
 * 文件包名：com.dup.server.actions.platform
 * 文件名称：DemoAction.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月6日 上午10:06:34
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.server.actions.platform;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dup.server.actions.platform.bean.CinemasQuery;
import com.dup.server.actions.platform.bean.CinemasReply;
import com.dup.server.services.platform.DemoFacade;

/**
 * 功能描述：
 * <p color="red">
 * 测试类，用来进行业务查询
 * </p>
 * 文件名称：DemoAction.java
 * 
 * @author ly
 */
@Controller
@RequestMapping("/api/v1/demo")
public class DemoAction extends APIAction {

    @Autowired
    private DemoFacade demoFacade;

    /**
     * 查询影院列表。
     * 请求示例：http://localhost:8080/api/v1/demo/queryCinemas.json?channelCode=0001&sign=0002
     * @param query
     *            请求对象
     * @return 返回响应对象。
     */
    @RequestMapping("queryCinemas")
    public CinemasReply queryCinemas(@Valid CinemasQuery query, HttpServletRequest req, HttpServletResponse resp) {
	StopWatch watch = new StopWatch();
	watch.start();
	CinemasReply result = demoFacade.queryCinemas(query);
	watch.stop();
	result.setCostTime(watch.getTotalTimeMillis());
	return result;
    }
    
    /**
     * 查询影院列表。
     * 请求示例：http://localhost:8080/api/v1/demo/queryCinemasTest.json?channelCode=0001&sign=0002
     * @param query
     *            请求对象
     * @return 返回响应对象。
     */
    @RequestMapping("queryCinemasTest")
    public CinemasReply queryCinemasTest(@Valid CinemasQuery query, HttpServletRequest req, HttpServletResponse resp) {
	StopWatch watch = new StopWatch();
	watch.start();
	CinemasReply result = demoFacade.queryCinemasTest(query);
	watch.stop();
	result.setCostTime(watch.getTotalTimeMillis());
	return result;
    }
}
