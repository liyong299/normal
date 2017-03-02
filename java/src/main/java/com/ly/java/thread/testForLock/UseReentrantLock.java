/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.testForLock
 * 文件名称：UseReentrantLock.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月1日 下午4:04:34
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.testForLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @功能描述：
 * @文件名称：UseReentrantLock.java
 * @author ly
 */
public class UseReentrantLock {

	private ReentrantLock lock = new ReentrantLock();

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new UseReentrantLock().modifyParamThread();
	}

	public void modifyParamThread() {
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					firstModifyParam();
				}
			}.start();
		}
	}

	public void firstModifyParam() {
		try {

			lock.lock();
			System.out.println(Thread.currentThread().getName() + " firstModifyParam locking ...");

			System.out.println("firstModifyParam Hello world!");

			System.out.println(Thread.currentThread().getName() + " firstModifyParam unlocking ...");

			secondModifyParam();
		} finally {
			lock.unlock();
		}
	}

	private void secondModifyParam() {
		try {

			lock.lock();
			System.out.println(Thread.currentThread().getName() + " secondModifyParam locking ...");

			System.out.println("secondModifyParam Hello world!");

			System.out.println(Thread.currentThread().getName() + " secondModifyParam unlocking ...");

		} finally {
			lock.unlock();
		}
	}
}
