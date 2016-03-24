/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread
 * 文件名称：MyThreadFactory.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月29日 下午4:55:37
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author ly
 *
 */
public class MyThreadFactory implements ThreadFactory
{

	/* (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	public Thread newThread(Runnable r)
	{
		return new Thread(r, "___" + System.nanoTime());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ThreadFactory tf = new MyThreadFactory();
		MyRunnable runnable = new MyRunnable();
		for (int i = 0; i < 10; i++)
		{
			runnable.setIdx(i);
			tf.newThread(runnable).start();
		}
	}

}

class MyRunnable implements Runnable
{
	int a = 0;
	int idx = 0;
	
	public void run()
	{
	    while (a < 1000)
	    {
	    	System.out.println(a++);
	    }
	}

	/**
	 * @return the idx
	 */
	public int getIdx()
	{
		return idx;
	}

	/**
	 * @param idx the idx to set
	 */
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
}