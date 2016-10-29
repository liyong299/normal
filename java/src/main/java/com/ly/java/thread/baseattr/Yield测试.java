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
 * 功能描述：<p color="red">调用yield方法会让当前线程交出CPU权限，让CPU去执行其他的线程。它跟sleep方法类似，同样不会释放锁。
 * 但是yield不能控制具体的交出CPU的时间，另外，yield方法只能让拥有相同优先级的线程有获取CPU执行时间的机会。
 * 注意，调用yield方法并不会让线程进入阻塞状态，而是让线程重回就绪状态，它只需要等待重新获取CPU执行时间，这一点是和sleep方法不一样的。</p>
 * 文件名称：Yield测试.java
 * @author ly
 */
public class Yield测试 {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
	int idx = 1;
	new Thread(new YieldThread(idx++)).start();
	new Thread(new YieldThread(idx++)).start();
    }

}
class YieldThread implements Runnable
{
    private int idx = 0;
    public YieldThread(int idx)
    {
	this.idx = idx;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
	System.out.println(System.currentTimeMillis() + "  " + Thread.currentThread().getName() + ",  start - idx : " + idx);
	long delay1 = (long) (Math.random() * 1000);
	long delay2 = (long) (Math.random() * 1000);
	System.out.printf(System.currentTimeMillis() + "  " + Thread.currentThread().getName() + ", 两次休眠时间： %5d %5d \n", delay1, delay2);
	try {
	    Thread.currentThread().sleep(delay1);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	Thread.currentThread().yield(); // 线程交出CPU让其他线程获取执行
	
	try {
	    Thread.currentThread().sleep(delay2);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println(System.currentTimeMillis() + "  " + Thread.currentThread().getName() + ",  end - idx : " + idx);
    }
    
}