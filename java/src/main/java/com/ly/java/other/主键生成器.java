/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.other
 * �ļ����ƣ�����������.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��3��11�� ����11:14:59
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.other;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ����������<p color="red">������������</p>
 * �ļ����ƣ�����������.java
 * @author ly
 */
public class ����������
{	
	// ������
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
        //����
        for(int i=0;i<1000;i++)
        {
            System.out.println(nextId());
        }        
    }

}
