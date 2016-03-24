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
		test();
		
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
}
