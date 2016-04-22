package com.ly.java.concurrent.blockqueue;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{  
    
    protected BlockingQueue<Object> queue = null;  
  
    public Consumer(BlockingQueue<Object> queue) {  
        this.queue = queue;  
    }  
  
    public void run() {  
        try {  
            System.out.println(new Date() + ",  " + queue.take());  
            System.out.println(new Date() + ",  " + queue.take());  
            System.out.println(new Date() + ",  " + queue.take());  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}  