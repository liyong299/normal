/**
 * 项目名称：java
 * 文件包名：com.ly.java.concurrent.synchronize
 * 文件名称：内存可见性.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月24日 下午3:00:15
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.concurrent.synchronize;

/**
 * 功能描述：
 * <p color="red">
 * 对象M上有两个变量x和y，当A进入代码块，锁定M，对x和y，进行编辑。线程B同时进入代码块，可以看到A做的操作。
 * </p>
 * 文件名称：内存可见性.java
 * 
 * @author ly
 */
public class 内存可见性2 {

    /**
     * <p>
     * 功能描述：本例是线程锁定了自己，被操作对象obj的对象变量，在发生变化
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

