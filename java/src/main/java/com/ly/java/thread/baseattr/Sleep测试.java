/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.baseattr
 * 文件名称：Yield测试.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年6月7日 下午3:34:17
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.baseattr;

/**
 * 功能描述：
 * <p color="red">
 * sleep方法不会释放锁，也就是说如果当前线程持有对某个对象的锁，则即使调用sleep方法，其他线程也无法访问这个对象。
 * 注意，如果调用了sleep方法，必须捕获InterruptedException异常或者将该异常向上层抛出。
 * 当线程睡眠时间满后，不一定会立即得到执行，因为此时可能CPU正在执行其他的任务。
 * 所以说调用sleep方法相当于让线程进入阻塞状态。
 * </p>
 * 文件名称：Sleep测试.java
 * 
 * @author ly
 */
public class Sleep测试 {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {

	int idx = 0;
	String str = "abc";
	new Thread(new SleepThread(idx++, str)).start();
	new Thread(new SleepThread(idx++, str)).start();
    }
}

class SleepThread implements Runnable {
    private int idx = 0;
    private String str = "";

    public SleepThread(int idx, String str) {
	this.idx = idx;
	this.str = str;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
	synchronized (str) {
	    System.out.println(System.currentTimeMillis() + "  " + Thread.currentThread().getName() + ",  start - idx : " + idx);
	    long delay1 = (long) (Math.random() * 1000);
	    long delay2 = (long) (Math.random() * 1000);
	    System.out.printf(System.currentTimeMillis() + "  " + Thread.currentThread().getName() + ", 两次休眠时间： %5d %5d \n", delay1, delay2);
	    try {
		Thread.currentThread().sleep(delay1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	    System.out.println(System.currentTimeMillis() + "  " + Thread.currentThread().getName() + ",  end - idx : " + idx);

	}
    }

}