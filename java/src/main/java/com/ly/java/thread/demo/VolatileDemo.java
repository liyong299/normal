/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.demo
 * 文件名称：voliatileDemo.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年1月5日 上午10:48:37
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.demo;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;


/**
 * @功能描述：
 * @文件名称：voliatileDemo.java
 * @author ly
 */
public class VolatileDemo {

	static volatile int count = 0; // 静态的也不能保持同步

	static final int threadCnt = 500;

	volatile int value = 0; // 非静态的也不能保持同步

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		//		test1();
		//		test2();
		//		test3();
		//		test4();
		//		test5();
		test6();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("当前值：" + count);
			}
		});
	}

	public static void test1() {
		for (int i = 0; i < threadCnt; i++) {
			new MyThread1().start();
		}
	}

	public static void test2() {
		for (int i = 0; i < threadCnt; i++) {
			new MyThread2().start();
		}
	}

	public static void test3() {
		for (int i = 0; i < threadCnt; i++) {
			new MyThread3().start();
		}
	}

	public static void test4() {
		for (int i = 0; i < threadCnt; i++) {
			new MyThread4().start();
		}
	}

	public static void test5() {
		VolatileDemo demo = new VolatileDemo();
		for (int i = 0; i < threadCnt; i++) {
			new MyThread5(demo).start();
		}

		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("当前值value：" + demo.value);
	}

	static MyAtomicInteger demo = new MyAtomicInteger();
	public static void test6() {

		for (int i = 0; i < threadCnt; i++) {
			new MyThread6().start();
		}

		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("当前值value：" + demo.value);
	}
}

// 自己定义个原子类
class MyAtomicInteger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4598159988196270719L;
	private static final Unsafe unsafe = Unsafe.getUnsafe(); // 业务代码中不允许使用它，它只能被bootloader加载。
	private static final long valueOffset;

	static {
		try {
			valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
		} catch (Exception ex) {
			throw new Error(ex);
		}
	}
	volatile int value = 0; // 非静态的也不能保持同步

	public boolean increment(int a) {
		int current = value;
		int next = current + a;
		return unsafe.compareAndSwapInt(this, valueOffset, current, next);
	}
}
class MyThread1 extends Thread {
	public void run() {
		try {
			sleep((long) (Math.random() * 100 + 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VolatileDemo.count++;
	}
}

class MyThread2 extends Thread {
	public void run() {
		try {
			sleep((long) (Math.random() * 100 + 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VolatileDemo.count += 1;
	}
}

class MyThread3 extends Thread {
	public void run() {
		try {
			sleep((long) (Math.random() * 100 + 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VolatileDemo.count = VolatileDemo.count + 1;
	}
}

class MyThread4 extends Thread {
	public void run() {
		add();
	}

	private final void add() {
		try {
			sleep((long) (Math.random() * 100 + 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VolatileDemo.count = VolatileDemo.count + 1;
	}
}

class MyThread5 extends Thread {
	private VolatileDemo demo;

	public MyThread5(VolatileDemo demo) {
		this.demo = demo;
	}
	public void run() {
		try {
			sleep((long) (Math.random() * 100 + 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		demo.value = demo.value + 1;
	}
}

class MyThread6 extends Thread {
	public void run() {
		try {
			sleep((long) (Math.random() * 100 + 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VolatileDemo.demo.increment(1);
	}
}