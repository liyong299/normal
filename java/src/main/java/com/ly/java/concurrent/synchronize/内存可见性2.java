/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.concurrent.synchronize
 * �ļ����ƣ��ڴ�ɼ���.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��4��24�� ����3:00:15
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.concurrent.synchronize;

/**
 * ����������
 * <p color="red">
 * ����M������������x��y����A�������飬����M����x��y�����б༭���߳�Bͬʱ�������飬���Կ���A���Ĳ�����
 * </p>
 * �ļ����ƣ��ڴ�ɼ���.java
 * 
 * @author ly
 */
public class �ڴ�ɼ���2 {

    /**
     * <p>
     * �����������������߳��������Լ�������������obj�Ķ���������ڷ����仯
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
	WorkObj obj = new WorkObj();
	new Thread(new MyWork1(obj)).start();
	new Thread(new MyWork2(obj)).start();
    }

}

class MyWork21 implements Runnable {
    private WorkObj obj;

    public MyWork21(WorkObj obj) {
	this.obj = obj;
    }

    public void run() {
	for (int i = 0; i < 10; i++) {
	    try {
		Thread.currentThread().sleep(500);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println(Thread.currentThread().getName() + "   " + obj.getX());
	}
    }
}

class MyWork22 implements Runnable {
    private WorkObj obj;

    public MyWork22(WorkObj obj) {
	this.obj = obj;
    }

    public void run() {
	for (int i = 0; i < 10; i++) {
		try {
		    Thread.currentThread().sleep(400);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		obj.setX(i);
		System.out.println(Thread.currentThread().getName() + "   " + obj.getX());
	    }
    }
}

