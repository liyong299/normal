/**
 * 项目名称：java
 * 文件包名：com.ly.java.concurrent.synchronize
 * 文件名称：关键字作用域.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月19日 上午9:26:37
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.concurrent.synchronize;

import java.util.Date;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：关键字作用域.java
 * @author ly
 */
public class 关键字作用域 {

    public static void main(String[] args)
    {
	Demo1 demo = new Demo1();
	for (int i = 0; i < 0; i++)
	{
	    new MyThread(demo).start();
	}
	
	test阻塞代码块不影响非阻塞代码块();
    }
    
    public static void test阻塞代码块不影响非阻塞代码块()
    {
	Demo1 demo = new Demo1();
	new MyThread(demo).start();
	new MyThread2(demo).start();
    }
}


class Demo1 {
    int t1 = 0;
    Object obj = new Object();
    int t2 = 0;

    public synchronized void test1()
    {
	t1++;
	System.out.printf("%10s %10s", t1, t2);
	System.out.println(Thread.currentThread().getName() + ",  " + new Date());
	try {
	    Thread.currentThread().sleep(5000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println(Thread.currentThread().getName() + ",  " + new Date());
    }
    public void test()
    {
//	t1++;
	this.setT1(this.getT1()+1);
	System.out.printf("%10s %10s", t1, t2);
	System.out.println(Thread.currentThread().getName() + ",  " + new Date());
    }
    
    public void test2()
    {
	synchronized (obj) {
	    t2++;
	    System.out.printf("%10s %10s", t1, t2);
	    System.out.println(Thread.currentThread().getName() + ",  " + new Date());
	}
    }
    /**
     * @return the t1
     */
    public int getT1() {
        return t1;
    }
    /**
     * @param t1 the t1 to set
     */
    public void setT1(int t1) {
        this.t1 = t1;
    }
}

class MyThread extends Thread
{
    private Demo1 demo;
    public MyThread(Demo1 demo)
    {
	this.demo = demo;
    }
    
    public void run()
    {
	int i = 0;
	while (i++ < 3)
	{
	    demo.test1();
	}
    }
}

class MyThread2 extends Thread
{
    private Demo1 demo;
    public MyThread2(Demo1 demo)
    {
	this.demo = demo;
    }
    public void run()
    {
	int i = 0;
	while (i++ < 10)
	{
	    demo.test();
	}
    }
}