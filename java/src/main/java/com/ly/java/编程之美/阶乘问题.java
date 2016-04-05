package com.ly.java.编程之美;

public class 阶乘问题
{
	static int random = 10;
	static int factorial = 1;
	static {
		random = (int) (Math.random() * 100 + 10);
		System.out.println("当前值： " + random + " ， 二进制值： " + Integer.toBinaryString(random));
		for (int i = 1; i < random; i++)
		{
//			factorial = factorial * i;
		}
	}
	public static void main(String[] args)
	{
		test1();
	}
	
	// 阶乘末尾有多少个0
	public static void test1()
	{
		int tmp = random, oneNum = 0;
		while (tmp > 0)
		{
			int j = tmp;
			if (j % 5 == 0)
			{
				oneNum++;
				j = j / 5; 
			}
			tmp--;
		}
		System.out.println("当前二进制数中的1的个数：" + oneNum);
	}
	
}
