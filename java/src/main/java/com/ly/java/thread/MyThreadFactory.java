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
	    while (a < 10)
	    {
	    	System.out.println(Thread.currentThread().getName() + "————————" + a++);
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