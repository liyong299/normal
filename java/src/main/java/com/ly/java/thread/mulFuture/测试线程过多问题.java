package com.ly.java.thread.mulFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 测试线程过多问题 {

	public static void main(String[] args) {
		测试线程过多问题 test = new 测试线程过多问题();
		for (int i = 0; i < 100000; i++) {
			test.test(i);
		}
	}

	public static void test(int idx) {
		ExecutorService result = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			result.submit(new MyTask2(i));
			System.out.println(idx + ":::::线程名：：：：" + Thread.currentThread().getName());
		}
	}

}
