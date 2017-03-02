/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.testForLock
 * 文件名称：ErrorUserSync.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月1日 下午3:38:00
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.testForLock;

/**
 * @功能描述：
 * @文件名称：ErrorUserSync.java
 * @author ly
 */
public class ErrorUseSynchrThread extends Thread {
	private int num = 0;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new ErrorUseSynchrThread().start();
		}
	}

	public synchronized void run() {

		for (int i = 0; i < 100; i++)
			System.out.println(num++);
	}
}
