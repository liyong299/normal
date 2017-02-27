/**
 * 项目名称：java
 * 文件包名：com.ly.java.other
 * 文件名称：TestForThreadSleep.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年2月27日 上午9:27:54
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.other;

import java.util.concurrent.TimeUnit;

/**
 * @功能描述：
 * @文件名称：TestForThreadSleep.java
 * @author ly
 */
public class TestForThreadSleep {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(" start = " + System.currentTimeMillis());
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("   end = " + System.currentTimeMillis());
	}

}
