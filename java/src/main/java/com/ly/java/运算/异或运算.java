/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.����
 * �ļ����ƣ��������.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��3��2�� ����3:04:34
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.����;

/**
 * ������������ͬ���0����ͬ���1
 * @author ly
 *
 */
public class �������
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		test1������ʱ��������(125, 365);
		test2������ʱ��������(125, 365);
	}
	
	/**
	 * ����������������ֵ
	 * @param a
	 * @param b
	 */
	public static void test1������ʱ��������(int a, int b)
	{
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.printf("%6d   %6d", a, b);
	}
	
	/**
	 * �����������������ֵ
	 * @param a
	 * @param b
	 */
	public static void test2������ʱ��������(int a, int b)
	{
		System.out.println();
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.printf("%6d   %6d", a, b);
	}
}
