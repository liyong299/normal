/**
 * 项目名称：java
 * 文件包名：com.ly.java.运算
 * 文件名称：与运算.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月2日 下午2:36:34
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.运算;

/**
 * @author ly
 *
 */
public class 与运算
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		test1与运算获取指定位数();
		test2与运算获取指定位数();
		test3奇偶判断();
	}

	public static void test1与运算获取指定位数()
	{
		System.out.println(123 & 0xff);
		System.out.println(329 & 0xff);
		System.out.println(0xff);
	}
	
	/**
	 * 二进制数使用补码表示，最高位为符号位，正数的符号位为0，负数为1
	 * 与运算规则：两个操作数中位都为1，结果才为1，否则结果为0
	 * 
	 */
	public static void test2与运算获取指定位数()
	{
		System.out.println(Integer.toBinaryString(329));
		System.out.println(Integer.toBinaryString(-123));
		System.out.println(Integer.toBinaryString(123));
		System.out.println(123 & 255);
		System.out.println(329 & 255);
		System.out.println(-123 & 255);
	}
	
	public static void test3奇偶判断()
	{
		int a = 232;
		System.out.println((a & 1) == 1);
	}
}
