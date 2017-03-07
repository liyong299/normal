/**
 * 项目名称：core
 * 文件包名：com.dup.test.thread
 * 文件名称：测试内存溢出1.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月12日 下午2:02:27
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author ly
 *
 */
public class 测试内存溢出1
{

	public static void main(String[] args) {  
		  
        for (int i = 0;; i++) {  
            System.out.println("i = " + i);  
            new Thread(new HoldThread()).start();  
        }  
    }  
  
}  
  
class HoldThread extends Thread {  
    CountDownLatch cdl = new CountDownLatch(1);  
  
    public HoldThread() {  
        this.setDaemon(true);  
    }  
  
    public void run() {  
        try {  
            cdl.await();  
        } catch (InterruptedException e) {  
        }  
    }  
}  
