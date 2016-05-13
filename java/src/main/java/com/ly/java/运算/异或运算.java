/**
 * 项目名称：java
 * 文件包名：com.ly.java.运算
 * 文件名称：异或运算.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月2日 下午3:04:34
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.运算;

/**
 * 异或运算规则：相同输出0，不同输出1
 * @author ly
 *
 */
public class 异或运算
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		test1不用临时变量交换(125, 365);
		test2不用临时变量交换(125, 365);
	}
	
	/**
	 * 异或操作，交换变量值
	 * @param a
	 * @param b
	 */
	public static void test1不用临时变量交换(int a, int b)
	{
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.printf("%6d   %6d", a, b);
	}
	
	/**
	 * 常规操作，交换变量值
	 * @param a
	 * @param b
	 */
	public static void test2不用临时变量交换(int a, int b)
	{
		System.out.println();
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.printf("%6d   %6d", a, b);
	}
}
