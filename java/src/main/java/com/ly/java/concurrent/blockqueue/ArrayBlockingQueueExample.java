package com.ly.java.concurrent.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ����������<p color="red">ArrayBlockingQueue ��ʵ���� BlockingQueue �ӿڡ�
ArrayBlockingQueue ��һ���н���������У����ڲ�ʵ���ǽ�����ŵ�һ��������н�Ҳ����ζ�ţ������ܹ��洢���޶�������Ԫ�ء�����һ��ͬһʱ���ܹ��洢Ԫ�����������ޡ�������ڶ����ʼ����ʱ���趨������ޣ���֮����޷���������޽����޸���(����ע����Ϊ���ǻ�������ʵ�ֵģ�Ҳ�;�����������ԣ�һ����ʼ������С���޷��޸�)��
ArrayBlockingQueue �ڲ��� FIFO(�Ƚ��ȳ�)��˳���Ԫ�ؽ��д洢�������е�ͷԪ��������Ԫ��֮���Ƿ���ʱ����õ��Ǹ�����βԪ��������̵��Ǹ���</p>
 * �ļ����ƣ�BlockingQueueExample.java
 * @author ly
 */
public class ArrayBlockingQueueExample {  
    
    public static void main(String[] args) throws Exception {  
  
        BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(1024);  
  
        Producer producer = new Producer(queue);  
        Consumer consumer = new Consumer(queue);  
  
        new Thread(producer).start();  
        new Thread(consumer).start();  
  
        Thread.sleep(4000);  
    }  
}  