/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.线程安全
 * 文件名称：赋值顺序.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月19日 下午7:05:57
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.线程安全;

/**
 * 功能描述：<p color="red">测试代码重新排序问题，但是未测试成功</p>
 * 文件名称：赋值顺序.java
 * @author ly
 */
public class 赋值顺序 {

    private static 赋值顺序 testObject = null;
    int a;
    public 赋值顺序() {
    	a = 100;
    	testObject = this;			//这个地方可能和a=100发生指令重排序
    }
    public static void read() {
    	if(testObject != null) {
    	    System.out.println(testObject.a);
    	}
    }
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
	new ModifyThread().start();
	
    }
    private static class ModifyThread extends Thread
    {
        public void run()
        {
            for (;;)
            {
        	赋值顺序 aa = new 赋值顺序();
        	new ReadThread1(aa).start();
        	new ReadThread1(aa).start();
        	new ReadThread1(aa).start();
        	new ReadThread1(aa).start();
        	new ReadThread1(aa).start();
        	new ReadThread1(aa).start();
            }
        }
    }
    
    private static class ReadThread1 extends Thread
    {
	private 赋值顺序 aa;
	public ReadThread1(赋值顺序 aa)
	{
	    this.aa = aa;
	}
        public void run()
        {
            aa.read();
        }
    }
}


