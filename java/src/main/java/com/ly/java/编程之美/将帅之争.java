/**
 * 项目名称：java
 * 文件包名：com.ly.java.编程之美
 * 文件名称：将帅之争.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月29日 下午4:47:48
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.编程之美;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：将帅之争.java
 * @author ly
 */
public class 将帅之争
{

	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
	 * @param args
	 */
	public static void main(String[] args)
	{
//		test1();
		test2();
	}
	static int num = 1;
	public static void test2()
	{
		long start = System.currentTimeMillis();
		for (int t = 0; t < num; t++)
		for (int i = 1; i < 82; i++)
		{
			if(i/9%3 != i%9%3)
			System.out.println("i = " + (i/9+1) + ", j = " + (i%9+1));
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public static void test1()
	{
		long start = System.currentTimeMillis();
		for (int t = 0; t < num; t++)
		for (int i = 1; i < 10; i++)
		{
			for (int j = 1; j < 10; j++)
			{
				if (j%3 != i%3);
//				System.out.println("i = " + i + ", j = " + j);
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}
