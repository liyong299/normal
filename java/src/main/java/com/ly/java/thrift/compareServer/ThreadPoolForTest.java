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

	private void testForTThreadPoolClient() throws InterruptedException {
		for (int i = 0; i < 200; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10; j++) {
						TThreadPoolClientDemo client = new TThreadPoolClientDemo();
						String aa = client.startClient("Michael");
					}
				}
			});
			TimeUnit.MILLISECONDS.sleep(200);
		}
	}
	
	/**
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		new ThreadPoolForTest().testForTThreadPoolClient();
	}

}
