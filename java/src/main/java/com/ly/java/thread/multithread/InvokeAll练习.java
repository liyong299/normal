/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.multithread
 * 文件名称：InvokeAll练习.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年11月18日 下午2:14:46
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * @功能描述：
 * @文件名称：InvokeAll练习.java
 * @author ly
 */
public class InvokeAll练习 {

	@Test
	public void test1() {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MyTask> list = new ArrayList();
		for (int i = 0; i < 10; i++) {
			list.add(new MyTask());
		}
		try {
			executor.invokeAll(list);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("         1              ");
	}

	@Test
	public void test2() {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++){
			executor.submit(new MyTask());
		}
		System.out.println("         1              ");
	}

	@Test
	public void test3() {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executor.execute(new MyTask2());
		}
		System.out.println("         1              ");
	}
}

class MyTask implements Callable<Integer> {

	public Integer call() throws Exception {
		System.out.println("---------------1");
		return 1;
	}
}

class MyTask2 implements Runnable {

	public void run() {
		System.out.println("---------------1");
	}
}