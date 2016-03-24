/**
 * 项目名称：java
 * 文件包名：com.ly.java.other
 * 文件名称：主键生成器.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月11日 上午11:14:59
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.other;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述：<p color="red">用以生成主键</p>
 * 文件名称：主键生成器.java
 * @author ly
 */
public class 主键生成器
{	
	// 对象锁
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static int count = 0;

    @SuppressWarnings("finally")
    public static String nextId() 
    {
        LOCK.lock();
        try {
                boolean done = false;
                while (!done) 
                {
                    long now = System.currentTimeMillis();
                    if (now == lastTime) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(1);
                        } catch (java.lang.InterruptedException e) {
                        }
                        continue;
                    } else {
                        lastTime = now;
                        done = true;
                    }
                }
        }
        finally 
        {
            LOCK.unlock();
            return lastTime+""+String.format("%03d",count); 
        }
    }
    public static void main(String[] args)
    {
        //测试
        for(int i=0;i<1000;i++)
        {
            System.out.println(nextId());
        }        
    }

}
