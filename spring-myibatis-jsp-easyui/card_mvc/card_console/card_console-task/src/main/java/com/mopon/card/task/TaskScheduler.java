package com.mopon.card.task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 功能描述：<p color="red">任务调度器，实现任务的调度</p>
 * 文件名称：TaskScheduler.java
 * @author ly
 */
@Component
public class TaskScheduler 
{
	private Logger log = LoggerFactory.getLogger(getClass());

	private static int nThreads = (Runtime.getRuntime().availableProcessors()) * 4;

	private static ThreadFactory demoTaskFactory = new NamedThreadFactory("demoTask_" + System.currentTimeMillis(), false);

	private static ExecutorService demoTaskThreadPool = Executors.newFixedThreadPool(nThreads, demoTaskFactory);
	{
		System.out.println("----------install TaskScheduler-----------");
	}
	/**
	 * 定时线程测试类,一个小时运行一次，用于检测线程类是否异常
	 */
	public void doTest() 
	{
		if (log.isInfoEnabled()) log.info("TaskScheduler.dotest>>>>>>>"  + "|now:" + System.currentTimeMillis());
	}

	/**
	 * 定时任务示例
	 */
	public void runDemo() {
		try {
			if (log.isInfoEnabled()) log.info("runDemo : " + "测试定时任务>>> begin; ");
			
			System.out.println("-----------------------");
			// 分布式部署时，实现竞争，或者分配  TODO
			if (true) 
			{
				CountDownLatch cdl = new CountDownLatch(1);
				
				demoTaskThreadPool.submit(new DemoTask(cdl));
				
				cdl.await();
				
				if (log.isInfoEnabled()) log.info("runDemo : " + "测试定时任务>>> end; ");
			}
		} catch (Exception e) {
			log.error("runDemo :" + "测试定时任务异常 >>> error; ", e);
		}
	}
}
