/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.multithread
 * 文件名称：多线程池关闭.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月30日 上午10:06:54
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：多线程池关闭.java
 * 
 * @author ly
 */
public class 多线程池关闭 {

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	testShutDown3();
    }

 // 而 shutdownNow() 方法阻止等待任务启动并试图停止当前正在执行的任务。
    // 在终止时，执行程序没有任务在执行，也没有任务在等待执行，并且无法提交新任务
    public static void testShutDown3() throws Exception {
	int idx = 0;
	while (idx++ < 3) {
	    System.out.println(System.currentTimeMillis() + "   " + idx++);
	    final ExecutorService exec = Executors.newFixedThreadPool(10);
	    List<NeedShutDownThread> tasks = new ArrayList<NeedShutDownThread>();
	    for (int i = 0; i < 15; i++) {
		tasks.add(new NeedShutDownThread());
	    }
	    
	    new Thread(){
		public void run()
		{
		    try {
			Thread.currentThread().sleep((long) (Math.random() * 3000));
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    exec.shutdownNow(); // 观察关闭线程池，对线程的影响
		}
	    }.start();
	    List<Future<String>> results = exec.invokeAll(tasks);
	    System.out.println(Thread.currentThread().getName() + " ---- " + "关闭准备 " + System.currentTimeMillis());
	    
	    
	    
	    Thread.currentThread().sleep(1000);
	    System.out.println(Thread.currentThread().getName() + " ---- " + "关闭结束 " + System.currentTimeMillis());
	}
    }

    
    // shutdown() 方法在终止前允许执行以前提交的任务
    public static void testShutDown2() throws Exception {
	int idx = 0;
	while (idx++ < 3) {
	    System.out.println(System.currentTimeMillis() + "   " + idx);
	    final ExecutorService exec = Executors.newFixedThreadPool(10);
	    List<NeedShutDownThread> tasks = new ArrayList<NeedShutDownThread>();
	    for (int i = 0; i < 15; i++) {
		tasks.add(new NeedShutDownThread());
	    }
	    
	    new Thread(){
		public void run()
		{
		    System.out.println(Thread.currentThread().getName() + " ---- " + "关闭准备 " + System.currentTimeMillis());
		    try {
			Thread.currentThread().sleep((long) (Math.random() * 3000));
			System.out.println(Thread.currentThread().getName() + " ---- " + "关闭中。。。 " + System.currentTimeMillis());
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    exec.shutdown(); // 观察关闭线程池，对线程的影响
		    System.out.println(Thread.currentThread().getName() + " ---- " + "关闭结束 " + System.currentTimeMillis());
		}
	    }.start();
	    List<Future<String>> results = exec.invokeAll(tasks);
	}
    }

    // shutdown的使用
    public static void testShutDown1() throws Exception {
	int idx = 0;
	while (idx++ < 3) {
	    System.out.println(System.currentTimeMillis() + "   " + idx++);
	    ExecutorService exec = Executors.newFixedThreadPool(10);
	    List<NeedShutDownThread> tasks = new ArrayList<NeedShutDownThread>();
	    for (int i = 0; i < 15; i++) {
		tasks.add(new NeedShutDownThread());
	    }
	    List<Future<String>> results = exec.invokeAll(tasks);
	    for (Future<String> result : results) {

	    }
	    System.out.println(Thread.currentThread().getName() + " ---- " + "关闭准备 " + System.currentTimeMillis());
	    exec.shutdown(); // 观察关闭线程池，对线程的影响
	    Thread.currentThread().sleep(1000);
	    System.out.println(Thread.currentThread().getName() + " ---- " + "关闭结束 " + System.currentTimeMillis());
	}
    }
}

class NeedShutDownThread implements Callable<String> {
    public void run() {
//	System.out.println(Thread.currentThread().getName() + " ---- " + "休息开始  " + System.currentTimeMillis());
	try {
	    Thread.currentThread().sleep((long) (Math.random() * 1000));
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println(Thread.currentThread().getName() + " ---- " + "休息结束  " + System.currentTimeMillis());
    }

    public String call() throws Exception {
	run();
	return "aa";
    }
}
