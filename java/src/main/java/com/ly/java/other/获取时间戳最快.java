/**
 * 项目名称：java
 * 文件包名：com.ly.java.other
 * 文件名称：获取时间戳最快.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年12月3日 上午11:12:11
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.other;

import java.util.Calendar;
import java.util.Date;

/**
 * @功能描述：
 * @文件名称：获取时间戳最快.java
 * @author ly
 */
public class 获取时间戳最快 {
	private static long _TEN_THOUSAND = 10000;

	public static void main(String[] args) throws InterruptedException {
		long times = 1000 * _TEN_THOUSAND;
		long t1 = System.currentTimeMillis();
		testSystem(times);
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);

		Thread.sleep(1000);
		testCalander(times);
		long t3 = System.currentTimeMillis();
		System.out.println(t3 - t2 - 1000);

		Thread.sleep(1000);
		testDate(times);
		long t4 = System.currentTimeMillis();
		System.out.println(t4 - t3 - 1000);

		Thread.sleep(1000);
		testSystemNon(times);
		long t5 = System.currentTimeMillis();
		System.out.println(t5 - t4 - 1000);
	}

	public static void testSystem(long times) {//use 188  
		for (int i = 0; i < times; i++) {
			long currentTime = System.currentTimeMillis();
		}
	}

	public static void testCalander(long times) {//use 6299  
		for (int i = 0; i < times; i++) {
			long currentTime = Calendar.getInstance().getTimeInMillis();
		}
	}

	public static void testDate(long times) {
		for (int i = 0; i < times; i++) {
			long currentTime = new Date().getTime();
		}

	}

	public static void testSystemNon(long times) {
		for (int i = 0; i < times; i++) {
			long currentTime = System.nanoTime();
		}
	}
}
