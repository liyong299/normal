/**
 * 项目名称：java
 * 文件包名：com.ly.java.concurrent.base
 * 文件名称：守护线程.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月22日 上午9:53:13
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.concurrent.base;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述：
 * <p color="red">
 * 一个线程不断修改业务对象的某一个值，当超过指点时间间隔，没有修改，则重新启动线程
 * </p>
 * 文件名称：守护线程.java
 * 
 * @author ly
 */
public class 守护线程 {

    /**
     * <p>
     * 功能描述：
     * <p>
     * 方法功能
     * </p>
     * </p>
     * <p>
     * 实现逻辑：
     * <p>
     * 实现步骤
     * </p>
     * </p>
     * 
     * @param args
     */
    public static void main(String[] args) {
	ExecutorService threadPool = Executors.newCachedThreadPool();
	WorkObject obj = new WorkObject();

	new Thread(new WorkThread(obj)).start();
	new DaemonThread(threadPool, obj).start();
    }
}

class WorkThread implements Runnable {
    private WorkObject obj;

    public WorkThread(WorkObject obj) {
	this.obj = obj;
    }

    public void run() {
	while (true) {
	    System.out.println(Thread.currentThread().getName() + "   " + obj.getObj().addAndGet(1) + "   " + new Date());
	    obj.setDate(System.nanoTime());
	    try {
		Thread.currentThread().sleep(1000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

    }
}

class DaemonThread extends Thread {
    private ExecutorService threadPool;
    private WorkObject obj;

    public DaemonThread(ExecutorService threadPool, WorkObject obj) {
	this.threadPool = threadPool;
	this.obj = obj;
	setDaemon(true);
    }

    public void run() {
	while (true) {
	   System.out.println(Thread.currentThread().getName() + "   " + obj.getObj().decrementAndGet() + "   " + new Date());
	    
	    try {
		Thread.currentThread().sleep(1000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}

//
class WorkObject {
    private long date = System.nanoTime();
    private AtomicInteger obj = new AtomicInteger();

    /**
     * @return the date
     */
    public long getDate() {
	return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(long date) {
	this.date = date;
    }

    /**
     * @return the obj
     */
    public AtomicInteger getObj() {
	return obj;
    }

    /**
     * @param obj
     *            the obj to set
     */
    public void setObj(AtomicInteger obj) {
	this.obj = obj;
    }
}
