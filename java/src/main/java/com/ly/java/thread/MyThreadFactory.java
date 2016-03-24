/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.thread
 * �ļ����ƣ�MyThreadFactory.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��29�� ����4:55:37
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
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