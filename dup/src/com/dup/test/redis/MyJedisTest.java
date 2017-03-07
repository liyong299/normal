package com.dup.test.redis;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.FileUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class MyJedisTest
{
	public static void main(String[] args)
	{
		JedisPoolConfig config = new JedisPoolConfig();  
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
        config.setMaxTotal(50);  
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
        config.setMaxIdle(5);  
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
        config.setMaxWaitMillis(1000 * 3);  
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
        config.setTestOnBorrow(true);  
        JedisPool pool = new JedisPool(config, "172.16.34.9", 6379);  
        		
		long start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		String dirPerffix = "redis_test/redis-1/";
		for (int j = 0; j < 50; j++)
		{
			int size = 10;
			int perNum = 1000;
			CountDownLatch latch = new CountDownLatch(size);// 两个工人的协作
			
			long start2 = System.currentTimeMillis();
			for (int i = 0; i < size; i++)
			{
				String fileName = dirPerffix + j + "/" + i + "/";
				new Thread(new MyTask3(latch, pool, perNum, fileName)).start();
			}
			try
			{
				latch.await();// 等待所有工人完成工作
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			long resume = (System.currentTimeMillis() - start2);
			sb.append(new Date() + " | 每次调用耗时：" + j + "_" + resume + "\r\n");
			System.out.println(new Date() + " |  每次调用耗时：" + (resume));
		}
		System.out.println(new Date() + " | 总调用耗时：" + (System.currentTimeMillis() - start));
		sb.append(new Date() + " | 总调用耗时：" + "all_" + (System.currentTimeMillis() - start) + "\r\n");
		
		try
		{
			FileUtils.write(new File(dirPerffix + "time.txt"), sb.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		pool.destroy();
	}
}

class MyTask3 implements Runnable
{
	private CountDownLatch latch;
	private JedisPool pool;
	private int perNum;
	private String fileName;
	
	public MyTask3(CountDownLatch latch, JedisPool pool, int perNum, String fileName)
	{
		this.latch = latch;
		this.pool = pool;
		this.perNum = perNum;
		this.fileName = fileName;
	}

	@Override
	public void run()
	{
		try
		{
			String flag = Math.random() * 10000 + "";
			
			Jedis master = pool.getResource();
			for (int i = 0; i < perNum; i++)
			{
				master.set(flag + "_ausername" + i, "test_" + i);
				master.get(flag + "_ausername" + i);
			}
			Set<String> set = master.keys(flag + "_*");
			int size = set.size();
			System.out.println("线程表示：" + flag + " : " + size);
			if (size != perNum)
			{
//				System.out.println("发生错误----线程表示：" + flag + " : " +  set);
				try
				{
					fileName =  fileName + "_" + flag + "_error";
					String content = "发生错误----线程表示：" + flag + " : " 
							+ "操作总数：" + perNum + " : "
							+ "实际插入条数：" + size + " : "
							+  set;
					FileUtils.writeStringToFile(new File(fileName), content);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				for (String key : set)
				{
					master.del(key);
				}
			}
			
			master.close();
		}
		finally
		{
			latch.countDown();
		}
	}

}