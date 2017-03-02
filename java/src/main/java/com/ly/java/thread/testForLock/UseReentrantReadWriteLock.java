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

import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @功能描述：发现没什么地方可以用，就暂时先不demo了
 * @文件名称：UseReentrantLock.java
 * @author ly
 */
public class UseReentrantReadWriteLock {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	private ReadLock readLock = lock.readLock();

	private WriteLock writeLock = lock.writeLock();

	private Queue<Integer> queue;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	}

	public void add(Integer delat) {
		writeLock.lock();
		queue.add(delat);
		writeLock.unlock();
	}

	public int get() {
		return this.queue.peek();
	}
}
