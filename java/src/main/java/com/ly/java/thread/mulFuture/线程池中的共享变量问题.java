package com.ly.java.thread.mulFuture;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 线程池中的共享变量问题 {

	static ThreadLocal<String> threadKey = new ThreadLocal<>();
	public static void main(String[] args) {
		ExecutorService result = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			result.submit(new MyThreadLocalTask(1));
		}

		for (int i = 0; i < 10; i++) {
			result.submit(new MyThreadLocalTask(2));
		}
	}
}

class MyThreadLocalTask implements Callable<Map> {

	private int idx;

	public MyThreadLocalTask(int i) {
		idx = i;
	}

	@Override
	public Map call() throws Exception {
		Thread.currentThread().sleep(100);
		if (idx % 2 == 0) {
			线程池中的共享变量问题.threadKey.set("" + idx);
			System.out.println(":::::线程名：：：：" + Thread.currentThread().getName());
		} else {
			System.out.println(":::::线程名：：：：" + Thread.currentThread().getName());
			System.out.println(线程池中的共享变量问题.threadKey.get());
		}
		return null;
	}
}