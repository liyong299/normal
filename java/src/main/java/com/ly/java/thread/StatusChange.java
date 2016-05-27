/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread
 * 文件名称：StatusChange.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月27日 下午4:38:14
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：<p color="red">线程的各种状态</p>
 * 文件名称：StatusChange.java
 * @author ly
 */
public class StatusChange {

    static List<String> list;
    static {
	list = new ArrayList<String>();
	list.add("aaaaa");
    }
    
    /**
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
	
	ExecutorService es = Executors.newFixedThreadPool(20);
	es.execute(new MyThread1(list));
	System.out.println(Thread.currentThread().getName() + " --- " + list);
	Thread.currentThread().sleep(40000);
	list.notifyAll();
	Thread.currentThread().sleep(10000);
	System.out.println(Thread.currentThread().getName() + " --- " + list);
    }
}

class MyThread1 extends Thread
{
    private List<String> list;
    public MyThread1(List<String> list)
    {
	this.list = list;
    }
    public void run()
    {
	try {
	    Thread.currentThread().sleep(5000);
	    list.add("bbb");
	    wait();
	    System.out.println(Thread.currentThread().getName() + " --- " + list);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
