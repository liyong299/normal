/**
 * 项目名称：java
 * 文件包名：com.ly.java.thrift.compareServer
 * 文件名称：ThreadPoolForTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月16日 上午11:01:58
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thrift.compareServer;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @功能描述：
 * @文件名称：ThreadPoolForTest.java
 * @author ly
 */
public class ThreadPoolForTest {

	private ExecutorService executorService = Executors.newFixedThreadPool(200);

	/**
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		new ThreadPoolForTest().testForTThreadPoolClient();
	}

	public void testForTHsHaClient() throws InterruptedException {
		testForClient(THsHaClientDemo.class);
	}

	public void testForTNonblockingClient() throws InterruptedException {
		testForClient(TNonblockingClientDemo.class);
	}

	public void testForTThreadedSelectorClient() throws InterruptedException {
		testForClient(TThreadedSelectorClientDemo.class);
	}

	public void testForTThreadPoolClient() throws InterruptedException {
		testForClient(TThreadPoolClientDemo.class);
	}

	private void testForClient(final Class cls) throws InterruptedException {
		for (int i = 0; i < 200; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						try {
							Method method = cls.getMethod("startClient", String.class);
							method.invoke(cls.newInstance(), "Michael");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			TimeUnit.MILLISECONDS.sleep(1000);
		}
	}
}
