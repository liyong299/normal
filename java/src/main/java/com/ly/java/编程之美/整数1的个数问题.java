/**
 * 项目名称：java
 * 文件包名：com.ly.java.编程之美
 * 文件名称：整数1的个数问题.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月31日 上午8:59:43
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.编程之美;

/**
 * 功能描述：<p color="red">给定一个数，写下1到它之间的所有整数，然后数一下其中1的个数。</p>
 * 文件名称：整数1的个数问题.java
 * @author ly
 */
public class 整数1的个数问题
{
	static int random = 100;
	static {
		random = (int) (Math.random() * 100 + 10);
//		random = 100000000;
		System.out.println("当前值： " + random + " ， 二进制值： " + Integer.toBinaryString(random));
	}
	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
	 * @param args
	 */
	public static void main(String[] args)
	{
		test1();
		test2();
	}

	public static void test1()
	{
		long start = System.currentTimeMillis();
		int tmp = random, oneNum = 0;
		while (tmp > 0)
		{
			// 计算每一个数中的1的个数
			int j =  String.valueOf(tmp).length() - String.valueOf(tmp).replaceAll("1", "").length();
//			if (j > 0) System.out.printf("%5s  %3d  ", tmp,  j);
//			if (tmp % 5 == 4) System.out.println();
			oneNum = oneNum + j;
			tmp--;
		}
		System.out.println("所有数中的1的个数：" + oneNum + ", 运行时长" + (System.currentTimeMillis() - start));
	}
	
	public static void test2()
	{
		long start = System.currentTimeMillis();
		int tmp = random, oneNum = 0;
		while (tmp > 0)
		{
			// 计算每一个数中的1的个数
			int j = tmp;
			while (j > 0)
			{
				if (j % 10 == 1) oneNum++;
				j = j / 10;
			}
			tmp--;
		}
		System.out.println("所有数中的1的个数：" + oneNum + ", 运行时长" + (System.currentTimeMillis() - start));
	}
	
	// 根据整数的每一位数字可能出现1的规律，进行总结计算，暂未实现
	public static void test3()
	{
		long start = System.currentTimeMillis();
		int tmp = random, oneNum = 0;
		while (tmp > 0)
		{
			// 计算每一个数中的1的个数
			int j = tmp;
			while (j > 0)
			{
				if (j % 10 == 1) oneNum++;
				j = j / 10;
			}
			tmp--;
		}
		System.out.println("所有数中的1的个数：" + oneNum + ", 运行时长" + (System.currentTimeMillis() - start));
	}
	
	// 计算1的个数等于当前整数的数，范围0--int的最大值。
		public static void test4()
		{
			long start = System.currentTimeMillis();
			int tmp = random, oneNum = 0;
			while (tmp > 0)
			{
				// 计算每一个数中的1的个数
				int j = tmp;
				while (j > 0)
				{
					if (j % 10 == 1) oneNum++;
					j = j / 10;
				}
				tmp--;
			}
			System.out.println("所有数中的1的个数：" + oneNum + ", 运行时长" + (System.currentTimeMillis() - start));
		}
}
