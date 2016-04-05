/**
 * 项目名称：java
 * 文件包名：com.ly.java.编程之美
 * 文件名称：数字_子集和最大.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月30日 上午9:42:26
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.编程之美;


/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：数字_子集和最大.java
 * @author ly
 */
public class 数字_子集和最大
{
	static int arrLen = 100;
	static int subsetLen = 10;
	static int midVal = 10;
	static int[] arr = null;
	static {
		arrLen = (int) (Math.random() * 100 + midVal + 1);
		subsetLen = (int) (Math.random() * midVal + 1);
		
		System.out.println("数组长度： " + arrLen + " ， 子集长度： " + subsetLen);
		arr = new int[arrLen];
		for (int i = 0; i < arrLen; i++)
		{
			arr[i] = (int) (Math.random() * 10 + 1);
			System.out.printf("%3d  %3d , ", arr[i], i);
			if (i % subsetLen == subsetLen - 1)
			{
				System.out.println();
			}
		}
		System.out.println();
	}
	
	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
	 * @param args
	 */
	public static void main(String[] args)
	{
		test1();
	}

	public static void test1()
	{
		int maxSubsetSum = 0;
		int startIdx = 0, endIdx = subsetLen;
		for (int i = 0; i < subsetLen; i++)
		{
			maxSubsetSum = maxSubsetSum + arr[i];
		}
		System.out.println("初始子集和： " + maxSubsetSum);
		int tmpMaxSubsetSum = maxSubsetSum; // 计算出第一个子集之和
		
		for (int i = subsetLen; i < arrLen; i++)
		{
			// 计算出后续每一个子集之和，然后和之前的最大值比较
			tmpMaxSubsetSum = tmpMaxSubsetSum + arr[i] - arr[i - subsetLen];
			
			if (tmpMaxSubsetSum > maxSubsetSum)
			{
				maxSubsetSum = tmpMaxSubsetSum;
				startIdx = i - subsetLen + 1;
				endIdx = i;
//				System.out.println("最大子集和： " + maxSubsetSum + ", 开始下标： " + startIdx + ", 结束下标： " + endIdx);
			}
		}
		System.out.println("最大子集和： " + maxSubsetSum + ", 开始下标： " + startIdx + ", 结束下标： " + endIdx);
	}
}
