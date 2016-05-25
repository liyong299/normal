/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.线程安全
 * 文件名称：NoVisiabilityTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月17日 下午7:49:12
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.线程安全;

/**
 * 功能描述：<p color="red">通过修改JAVA_HOME/jre/lib/i386/jvm.cfg的JVM模式（client/server）会发现结果不同，且不可思议</p>
 * 文件名称：NoVisiabilityTest.java
 * @author ly
 */
public class NoVisiabilityTest 
{
	public static void main(String []args) throws InterruptedException {
		ReadThread readThread = new ReadThread();
		readThread.start();
		Thread.sleep(200);
		readThread.readyOn();
		System.out.println("-1---3---" + readThread.ready);
		
		ReadThread2 readThread2 = new ReadThread2();
		readThread2.start();
		Thread.sleep(200);
		readThread2.readyOn();
		System.out.println("-2---3---" + readThread2.ready);
	}
	
	private static class ReadThread extends Thread {

		private boolean ready;  // Server模式下子线程无法及时看到变量被修改。

		private int number;

		{
		    System.out.println("-1--1----" + ready);
		}
		public void run() {
			while(!ready) {
				number++;
			}
			System.out.println("-1----2--" + ready);
		}

		public void readyOn() {
			this.ready = true;
		}
	}
	
	private static class ReadThread2 extends Thread {

		private volatile boolean ready;  // 原子性保证，可以让变量在一定情况下，保持内存一致性

		private int number;

		{
		    System.out.println("-2--1----" + ready);
		}
		public void run() {
			while(!ready) {
				number++;
			}
			System.out.println("-2----2--" + ready);
		}

		public void readyOn() {
			this.ready = true;
		}
	}
}