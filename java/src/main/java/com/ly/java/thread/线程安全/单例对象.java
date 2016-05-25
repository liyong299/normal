package com.ly.java.thread.线程安全;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：<p color="red">单例对象的类变量，被重新赋值问题，导致前后获取的值可能不一样</p>
 * 文件名称：单例对象.java
 * @author ly
 */
public class 单例对象 {

    private Integer aa = null;
    private StringBuffer cc = new StringBuffer();
    
    private static 单例对象 bb = new 单例对象();
//    public static 单例对象 getInstanll()
//    {
//	return bb;
//    }
//    private 单例对象(){};
    
    public static void main(String[] args) {
	ExecutorService exec = Executors.newFixedThreadPool(100);
	for (int i = 0; i < 100; i++)
	{
	    exec.execute(new 修改类(i));
	}
    }

    private static class 修改类 implements Runnable{
	private int idx ; 
	public 修改类(int idx)
	{
	    this.idx = idx;
	}
	public void run() {
	    System.out.println(bb.aa);
	    bb.aa = idx;
	    System.out.println("idx :  " + idx + "     " + bb.aa);
	}
	
    }
}
