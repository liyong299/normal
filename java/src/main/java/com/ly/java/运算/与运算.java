/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.����
 * �ļ����ƣ�������.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��3��2�� ����2:36:34
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.����;

/**
 * @author ly
 *
 */
public class ������
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		test1�������ȡָ��λ��();
		test2�������ȡָ��λ��();
		test3��ż�ж�();
	}

	public static void test1�������ȡָ��λ��()
	{
		System.out.println(123 & 0xff);
		System.out.println(329 & 0xff);
		System.out.println(0xff);
	}
	
	/**
	 * ��������ʹ�ò����ʾ�����λΪ����λ�������ķ���λΪ0������Ϊ1
	 * ���������������������λ��Ϊ1�������Ϊ1��������Ϊ0
	 * 
	 */
	public static void test2�������ȡָ��λ��()
	{
		System.out.println(Integer.toBinaryString(329));
		System.out.println(Integer.toBinaryString(-123));
		System.out.println(Integer.toBinaryString(123));
		System.out.println(123 & 255);
		System.out.println(329 & 255);
		System.out.println(-123 & 255);
	}
	
	public static void test3��ż�ж�()
	{
		int a = 232;
		System.out.println((a & 1) == 1);
	}
}
