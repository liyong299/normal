/**
 * 项目名称：dup
 * 文件包名：com.dup.test.thread.concurrent
 * 文件名称：FutureTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月7日 上午10:56:23
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.thread.concurrent;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author ly
 *
 */
public class FutureTest
{

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		
	}
	
	@Test
	public void testListnableFuture()
	{
		int NUM_THREADS = 10;//10个线程
		ExecutorService executor = Executors
				.newFixedThreadPool(NUM_THREADS);
		
		ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);

		ListenableFuture<String> listenableFuture = executorService.submit(new Callable<String>()
		{
			@Override
			public String call() throws Exception
			{
				System.out.printf("%S %S", Thread.currentThread().getName(), ("------------------"));
				System.out.println();
				return "101";
			}
		});
		listenableFuture.addListener(new Runnable()
		{
			@Override
			public void run()
			{
				//在Future任务完成之后运行的一些方法
				System.out.println("method To Run On FutureTask Completion");
			}
		}, executorService);
		
		listenableFuture.addListener(new Runnable()
		{
			@Override
			public void run()
			{
				//在Future任务完成之后运行的一些方法
				System.out.println("method2 To Run On FutureTask Completion");
			}
		}, executorService);
		
		try
		{
			System.out.printf("%S %S %s", Thread.currentThread().getName(), (listenableFuture.get() + ""), new Date());
		}
		catch (InterruptedException | ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFuture()
	{
		ExecutorService executor = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 100; i++)
		{
			final int b = i;
			Future<Integer> future = executor.submit(new Callable<Integer>()
			{
				@Override
				public Integer call() throws Exception
				{
					//这里调用一些处理逻辑
					System.out.printf("%S %S", Thread.currentThread().getName(), (b));
					System.out.println();
					return b * 2;
				}
			});
			try
			{
				System.out.printf("%S %S %s", Thread.currentThread().getName(), (future.get() + ""), new Date());
			}
			catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
			System.out.println();
		}

	}

}
