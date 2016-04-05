/**
 * 项目名称：java
 * 文件包名：com.ly.java.编程之美
 * 文件名称：二进制中一的个数.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月30日 下午3:46:30
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.编程之美;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：二进制中一的个数.java
 * @author ly
 */
public class 二进制中一的个数
{
	static int random = 100;
	static {
		random = (int) (Math.random() * 100 + 10);
		System.out.println("当前值： " + random + " ， 二进制值： " + Integer.toBinaryString(random));
	}
	
	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
	 * @param args
	 */
	public static void main(String[] args)
	{
		test4();
	}
	
	// a变成b需要改变多少位bit。即，a和b有多少位不同
	public static void test4()
	{
		int a = 23, b =25;
		int c = a ^ b;
		System.out.printf("%10s  %10s  %10s  ", Integer.toBinaryString(a), Integer.toBinaryString(b), Integer.toBinaryString(c));
		System.out.println();
		
	}
	
	public static void test1()
	{
		int tmp = random, oneNum = 0;
		while (tmp > 0)
		{
			if ((tmp & 1) == 1)
			{
				oneNum++;
			}
			tmp = tmp >> 1;
		}
		System.out.println("当前二进制数中的1的个数：" + oneNum);
	}
	
	public static void test2()
	{
		int tmp = random, oneNum = 0;
		while (tmp > 0)
		{
			oneNum = oneNum + (tmp & 0x01);   // 最后一位是否为1，如果为1，则加1
			tmp = tmp >> 1;
		}
		System.out.println("当前二进制数中的1的个数：" + oneNum);
	}
	
	public static void test3()
	{
		int tmp = random, oneNum = 0;
		while (tmp > 0)
		{
			tmp = tmp & (tmp - 1);  // 每次抵消掉最小的2的幂。
			oneNum++;
		}
		System.out.println("当前二进制数中的1的个数：" + oneNum);
		
		// 下面一段还没有完成，但是不想做了，大概思路：1-255之间，所有的数的1的个数，具有一定的分布特点 比如：0 1 1 2， 1 2 2 3。。。可以根据这个特点处理
		int a = random / 16; 
		int b = a / 4; 
		int c = b / 2; 
		
		if (a <= 0)
		{
			oneNum = a - 1;
			System.out.println("1当前二进制数中的1的个数：" + oneNum);
			return;
		}
		if (b <= 0)
		{
			System.out.println("2当前二进制数中的1的个数：" + (oneNum + 1));
			return;
		}
		if (c > 0)
		{
			System.out.println("3当前二进制数中的1的个数：" + (oneNum));
			return;
		}
	}
}
