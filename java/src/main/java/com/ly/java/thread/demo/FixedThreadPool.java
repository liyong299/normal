/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.demo
 * 文件名称：FixedThreadPool.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年1月4日 下午3:27:49
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @功能描述：观察固定大小线程池时，阻塞队列过大的问题
 * 会抛出异常： GC overhead limit exceeded
 * GC overhead limt exceed检查是Hotspot VM 1.6定义的一个策略，通过统计GC时间来预测是否要OOM了，提前抛出异常，防止OOM发生。
 * Sun 官方对此的定义是：“并行/并发回收器在GC回收时间过长时会抛出OutOfMemroyError。过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存。用来避免内存过小造成应用不能正常工作。“
 * @文件名称：FixedThreadPool.java
 * @author ly
 */
public class FixedThreadPool {

	static ExecutorService service = Executors.newFixedThreadPool(100); // 默认阻塞队列有Integer.MAX_VALUE个值。
	static AtomicInteger count = new AtomicInteger(0);

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new FixedThreadPool().test2();
	}

	public void test3() {
		Executors.newCachedThreadPool();
	}

	public void test2(){
		ExecutorService service = new ThreadPoolExecutor(50, 100, 3600, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(100));
		/**
		 * 阻塞队列只有100，线程有100万，会发生异常
		 * 异常内容：RejectedExecutionException: Task java.util.concurrent.FutureTask@130661d rejected from java.util.concurrent.ThreadPoolExecutor@19e3bdd[Running, pool size = 100, active threads = 100, queued tasks = 100, completed tasks = 0]
		 */
		try {
			for (int i = 0; i < 1000000; i++) { // 阻塞对咧
				int tmp = FixedThreadPool.count.incrementAndGet();
				System.out.println(tmp);
				service.submit(new MyTask3());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			service.shutdown();
		}
	}

	public void test1() {
		for (int i = 0; i < 10000; i++) {
			new Thread(new MyTask2()).start();
		}
	}
}

class MyTask2 implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 1000000; i++) {
			int tmp = FixedThreadPool.count.incrementAndGet();
			System.out.println(tmp);
			FixedThreadPool.service.submit(new MyTask3());
		}

	}
}

class MyTask3 implements Runnable {
	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
