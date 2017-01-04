/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.demo
 * 文件名称：几个重要方法.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年1月4日 下午2:10:47
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.demo;

/**
 * @功能描述：
 * @文件名称：几个重要方法.java
 * @author ly
 */
public class 几个重要方法 {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new MyTask().start();
	}

}

class MyTask extends Thread {
	private Object lock = new Object();

	public void run() {
		synchronized (lock) {
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.yield();
			lock.notify();
			System.out.println("-------------------");
		}
	}
}