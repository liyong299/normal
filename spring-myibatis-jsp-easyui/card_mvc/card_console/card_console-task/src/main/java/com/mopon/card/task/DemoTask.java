package com.mopon.card.task;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mopon.entity.console.base.BaseAccountExt;
import com.mopon.service.account.AccountService;
import com.mopon.util.SpringContextUtil;


/**
 * 功能描述：<p color="red">demo定时任务</p>
 * 文件名称：TestTask.java
 * @author ly
 */
public class DemoTask implements Callable<Void> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private  CountDownLatch cdl;
	/**
	 * 构造方法。
	 * @param cdl 
	 */
	public DemoTask(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@Override
	public Void call() throws Exception {
		try {
			System.out.println("run the demo!");
			AccountService accountService = (AccountService) SpringContextUtil.getBean("accountServiceImpl");
			BaseAccountExt user = accountService.query("admin");
			System.out.println(user);
		} catch (Exception e) {
			
			log.error("run it error!", e);
		}
		finally
		{
			cdl.countDown();
		}
		return null;
	}
}
