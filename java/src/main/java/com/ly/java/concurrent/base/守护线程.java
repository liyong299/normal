/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.concurrent.base
 * �ļ����ƣ��ػ��߳�.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��4��22�� ����9:53:13
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.concurrent.base;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ����������
 * <p color="red">
 * һ���̲߳����޸�ҵ������ĳһ��ֵ��������ָ��ʱ������û���޸ģ������������߳�
 * </p>
 * �ļ����ƣ��ػ��߳�.java
 * 
 * @author ly
 */
public class �ػ��߳� {

    /**
     * <p>
     * ����������
     * <p>
     * ��������
     * </p>
     * </p>
     * <p>
     * ʵ���߼���
     * <p>
     * ʵ�ֲ���
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
