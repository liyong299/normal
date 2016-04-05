/**
 * 项目名称：java
 * 文件包名：com.ly.java.编程之美
 * 文件名称：灵活的CPU.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月24日 下午3:06:21
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.编程之美;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：灵活的CPU.java
 * @author ly
 */
public class 灵活的CPU
{

	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException
	{
//		test直线();
//		test数组除();
//		System.out.println(Runtime.getRuntime().freeMemory());
		test一数两段();
	}
	
	public static void test一数两段() throws InterruptedException
	{
		int a = 10, b=64;
		int c = a|b;
		System.out.println(Integer.toBinaryString(c));
		
		int d = c & 0x0f;
		System.out.println(Integer.toBinaryString(d));
	}
	
	public static void test数组除() throws InterruptedException
	{
		int[] arr = {3,2,34,34,53,23,1221,322};
		int x = 0;
		for (int i = 0; i < arr.length; i++)  // 结果错误
		{
			x = arr[i] / arr[0];
			arr[i] = arr[i] / arr[0];
			System.out.println(x + ",   " + arr[i]);
		}
		
		arr = new int[]{3,2,34,34,53,23,1221,322};
		for (int i = arr.length - 1; i >= 0; i--)  // 结果正确
		{
			x = arr[i] / arr[0];
			arr[i] = arr[i] / arr[0];
			System.out.println(x + ",   " + arr[i]);
		}
	}
	
	public static void test() throws InterruptedException
	{
		long i = 1;
		while(true)
		{
//			long start = System.currentTimeMillis();
			for(i = 0; i < 13200000000l; i++);
			Thread.currentThread().sleep(30);
//			System.out.println(System.currentTimeMillis() - start);
		}
	}
	public static void test直线() throws InterruptedException
	{
		while(true)
		{
			for(int i = 0; i < 13200000000l; i++);
			Thread.currentThread().sleep(7);
		}
	}
}
