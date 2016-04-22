package com.ly.java.concurrent.blockqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {  
    
    public static void main(String[] args) throws InterruptedException {  
        DelayQueue queue = new DelayQueue();  
  
        DelayedObj element1 = new DelayedObj("1", 100);  
        DelayedObj element2 = new DelayedObj("0", 1000);  
  
        queue.put(element1);  
        queue.put(element2); 
  
        DelayedObj relement1 = (DelayedObj) queue.take();  
        DelayedObj relement2 = (DelayedObj) queue.take(); 
        
        System.out.println(relement1.getLevel());
        System.out.println(relement2.getLevel());
    }  
}
class DelayedObj implements Delayed
{
    String level;
    private final long time;
    public DelayedObj(String level, long timeout)
    {
	this.level = level;
	this.time = now() + timeout;
    }
    
    public int compareTo(Delayed o) {
	if (o == null) throw new RuntimeException("对象不能为空");
	if (!(o instanceof DelayedObj))  throw new RuntimeException("对象类型不对， cls : " + o.getClass());
	return this.level.compareTo(((DelayedObj)o).getLevel());
    }

    public long getDelay(TimeUnit unit) {
	long d = unit.convert(time - now(), TimeUnit.NANOSECONDS);
	return d;
    }
    
    /** Base of nanosecond timings, to avoid wrapping */
    private static final long NANO_ORIGIN = System.nanoTime();
    
    final static long now() {
        return System.nanoTime() - NANO_ORIGIN;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }
    
}