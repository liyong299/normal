/**
 * 项目名称：core
 * 文件包名：com.dup.test.thread
 * 文件名称：ScheduledExecutorServiceTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月11日 下午4:21:28
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ly
 *
 */
public class ScheduledExecutorServiceTest
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// 线程池能按时间计划来执行任务，允许用户设定计划执行任务的时间，int类型的参数是设定  
	    // 线程池中线程的最小数目。当任务较多时，线程池可能会自动创建更多的工作线程来执行任务  
    	ScheduledExecutorService scheduExec = Executors  
                .newScheduledThreadPool(3);  
    	
		// 十秒钟之后开始执行，之后每隔五秒钟执行一次
		scheduExec.scheduleWithFixedDelay(new ScheduledExecutorServiceTask(), 1000 * 5, 1000 * 2,  
                TimeUnit.MILLISECONDS);  
		try
		{
			Thread.sleep(1000 * 5);// 5秒钟之后添加一个新任务  
			
			// 3秒钟之后开始执行，之后每隔1秒钟执行一次
			scheduExec.scheduleWithFixedDelay(new ScheduledExecutorServiceTask(), 1000 * 3, 1000 * 3,  
	                TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}

class ScheduledExecutorServiceTask implements Runnable
{
	@Override
	public void run()
	{
		System.out.println(Thread.currentThread().getName() + "_welcome to china_" + System.nanoTime() / 100000);  
	}
}
