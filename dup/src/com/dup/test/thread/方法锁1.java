/**
 * 项目名称：core
 * 文件包名：com.dup.test.thread
 * 文件名称：方法锁1.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月21日 下午3:25:12
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ly
 * 
 */
public class 方法锁1
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		test100();
	}

	public static void test10()
	{
		Runnable r1 = new MyTask10();
		new Thread(r1).start();
		new Thread(r1).start();
	}
	
	public static void test100()
	{
		Runnable r1 = new MyTask100();
		new Thread(r1).start();
		new Thread(r1).start();
	}

	public static void test11()
	{
		Runnable r1 = new MyTask11();
		new Thread(r1).start();
		new Thread(r1).start();
	}

	public static void test12()
	{
		Runnable r1 = new MyTask12();
		new Thread(r1).start();
		new Thread(r1).start();
	}

	public static void test13()
	{
		Runnable r1 = new MyTask13();
		new Thread(r1).start();
		new Thread(r1).start();
	}
}

class MyTask10 implements Runnable
{
	@Override
	public void run()
	{
		// 重入锁，多线程操作，可能需要。
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		int i = 0;
		System.out.println(Thread.currentThread().getName() + "_" + i);
		try
		{
			Thread.currentThread().sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
		i = 2;
		System.out.println(Thread.currentThread().getName() + "_" + i);
	}
}

class MyTask100 implements Runnable
{
	@Override
	public void run()
	{
		test();
	}

	private static void test()
	{
		// 重入锁，多线程操作，可能需要。
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		int i = 0;
		System.out.println(Thread.currentThread().getName() + "_" + i);
		try
		{
			Thread.currentThread().sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
		i = 2;
		System.out.println(Thread.currentThread().getName() + "_" + i);
	}
}

class MyTask11 implements Runnable
{
	@Override
	public synchronized void run()
	{
		int i = 0;
		System.out.println(Thread.currentThread().getName() + "_" + i);
		try
		{
			Thread.currentThread().sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i = 2;
		System.out.println(Thread.currentThread().getName() + "_" + i);
	}
}

class MyTask12 implements Runnable
{
	@Override
	public void run()
	{
		test();
	}

	public synchronized void test()
	{
		int i = 0;
		System.out.println(Thread.currentThread().getName() + "_" + i);
		try
		{
			Thread.currentThread().sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i = 2;
		System.out.println(Thread.currentThread().getName() + "_" + i);
	}
}

class MyTask13 implements Runnable
{
	@Override
	public void run()
	{
		test();
	}

	Lock lock = new ReentrantLock();

	public void test()
	{
		lock.lock();
		int i = 0;
		System.out.println(Thread.currentThread().getName() + "_" + i);
		try
		{
			Thread.currentThread().sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
		i = 2;
		System.out.println(Thread.currentThread().getName() + "_" + i);
	}
}