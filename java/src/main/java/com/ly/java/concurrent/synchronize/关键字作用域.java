/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.concurrent.synchronize
 * �ļ����ƣ��ؼ���������.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��4��19�� ����9:26:37
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.concurrent.synchronize;

import java.util.Date;

/**
 * ����������<p color="red">ʵ�ּ���</p>
 * �ļ����ƣ��ؼ���������.java
 * @author ly
 */
public class �ؼ��������� {

    public static void main(String[] args)
    {
	Demo1 demo = new Demo1();
	for (int i = 0; i < 0; i++)
	{
	    new MyThread(demo).start();
	}
	
	test��������鲻Ӱ������������();
    }
    
    public static void test��������鲻Ӱ������������()
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