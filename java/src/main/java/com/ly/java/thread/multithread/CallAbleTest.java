/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.multithread
 * 文件名称：CallAbleTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月24日 上午11:48:07
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 功能描述：
 * <p color="red">
 * Callable的使用
 * </p>
 * 文件名称：CallAbleTest.java
 * 
 * @author ly
 */
public class CallAbleTest {

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	testOOM4();
    }

 // 申请固定大小的线程池，存在部分线程处理时间较长，但是线程之间可能需要等待，但是线程池每次使用完成之后，都不会关闭
    // 线程数会增加，会发生OOM
    public static void testOOM5() throws Exception {
   	int idx = 0;
   	while (true) {
   	    System.out.println(System.currentTimeMillis() + "   " + idx++);
   	    ExecutorService exec = Executors.newFixedThreadPool(40);
   	    CountDownLatch cdl = new CountDownLatch(100);
   	    for (int i = 0; i < 100; i++) {
   		exec.execute(new MyErrorWork3(i + "", cdl));
   	    }
   	   cdl.await();
   	    System.out.println("===========================");
//   		 exec.shutdown();  // 如果不调用改方法，会存在延迟回收的问题，最终导致OOM
   	    Thread.currentThread().sleep(1000);
   	}
       }
    
    
    // 申请固定大小的线程池，存在部分线程处理时间较长，但是线程之间可能需要等待，但是线程池每次使用完成之后，都会关闭
    // 线程数不会暴增，较难发生OOM
    public static void testOOM4() throws Exception {
   	int idx = 0;
   	while (true) {
   	    System.out.println(System.currentTimeMillis() + "   " + idx++);
   	    ExecutorService exec = Executors.newFixedThreadPool(40);
   	    CountDownLatch cdl = new CountDownLatch(100);
   	    for (int i = 0; i < 100; i++) {
   		exec.execute(new MyErrorWork3(i + "", cdl));
   	    }
   	   
   	    System.out.println("===========================");
   		 exec.shutdown();  // 如果不调用改方法，会存在延迟回收的问题，最终导致OOM
   	    Thread.currentThread().sleep(1000);
   	}
       }
    
    // 申请固定大小的线程池，存在部分线程处理时间较长，但是线程之间不存在等待关系，但是线程池每次使用完成之后，都会关闭
    // 线程数不会暴增，较难发生OOM
    public static void testOOM3() throws Exception {
   	int idx = 0;
   	while (true) {
   	    System.out.println(System.currentTimeMillis() + "   " + idx++);
   	    ExecutorService exec = Executors.newFixedThreadPool(40);
   	    for (int i = 0; i < 100; i++) {
   		exec.execute(new MyErrorWork2(i + ""));
   	    }
   	   
   	    System.out.println("===========================");
   		 exec.shutdown();  // 如果不调用改方法，会存在延迟回收的问题，最终导致OOM
   	    Thread.currentThread().sleep(1000);
   	}
       }
    
    // 申请固定大小的线程池，使用完成之后，就直接关闭
    // 线程数不会暴增，较难发生OOM
    public static void testOOM2() throws Exception {
   	int idx = 0;
   	while (true) {
   	    System.out.println(System.currentTimeMillis() + "   " + idx++);
   	    List<MyErrorWork> list = new ArrayList<MyErrorWork>();
   	    for (int i = 0; i < 100; i++) {
   		list.add(new MyErrorWork(i + ""));
   	    }
   	    ExecutorService exec = Executors.newFixedThreadPool(40);
   	    List<Future<String>> results = exec.invokeAll(list);
   	    for (Future<String> future : results) {
   		System.out.println(Thread.currentThread().getName() + " --- " + future.get());
   	    }
   	    System.out.println("===========================");
   		 exec.shutdown();  // 如果不调用改方法，会存在延迟回收的问题，最终导致OOM
   	    Thread.currentThread().sleep(1000);
   	}
       }
    
 // 申请固定大小的线程池，使用完成之后，就不进行关闭
    // 线程数会暴增，容易发生OOM
    public static void testOOM() throws Exception {
	int idx = 0;
	while (true) {
	    System.out.println(System.currentTimeMillis() + "   " + idx++);
	    List<MyErrorWork> list = new ArrayList<MyErrorWork>();
	    for (int i = 0; i < 100; i++) {
		list.add(new MyErrorWork(i + ""));
	    }
	    ExecutorService exec = Executors.newFixedThreadPool(40);
	    List<Future<String>> results = exec.invokeAll(list);
	    for (Future<String> future : results) {
		System.out.println(Thread.currentThread().getName() + " --- " + future.get());
	    }
	    System.out.println("===========================");
		// exec.shutdown();  // 如果不调用改方法，会存在延迟回收的问题，最终导致OOM
	    Thread.currentThread().sleep(1000);
	}
    }
}

class MyErrorWork implements Callable<String> {
    private String userID;

    public MyErrorWork(String userID) {
	this.userID = userID;
    }

    public String call() throws Exception {
	if (userID.length() > 1) {
	    System.out.println(Thread.currentThread().getName() + " --- " + "---- userID : " + userID);
	}
	return userID;
    }

}

class MyErrorWork2 implements Runnable {
    private String userID;

    public MyErrorWork2(String userID) {
	this.userID = userID;
    }
    public void run() {
	double d1 = Math.random() * 100;
	if (d1 > 50)
	{
	    try {
		Thread.currentThread().sleep(3000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	    System.out.println(Thread.currentThread().getName() + " --- " + "---- userID : " + userID);
	return ;
    }
}

class MyErrorWork3 implements Runnable {
    private String userID;
    private CountDownLatch cdl;

    public MyErrorWork3(String userID, CountDownLatch cdl) {
	this.userID = userID;
	this.cdl = cdl;
    }
    public void run() {
	try
	{
	    double d1 = Math.random() * 100;
		if (d1 > 50)
		{
			Thread.currentThread().sleep(3000);
		    
		}
		    System.out.println(Thread.currentThread().getName() + " --- " + "---- userID : " + userID);
	}
	catch(Exception ex)
	{
	    ex.printStackTrace();
	}
	finally
	{
	    if (this.cdl != null) this.cdl.countDown();
	}
	
	return ;
    }
}