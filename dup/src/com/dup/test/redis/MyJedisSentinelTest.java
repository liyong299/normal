package com.dup.test.redis;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class MyJedisSentinelTest
{
	public static void main(String[] args)
	{
		Set<String> sentinels = new HashSet<String>();
		sentinels.add(new HostAndPort("172.16.34.8", 26379).toString());
		sentinels.add(new HostAndPort("172.16.34.9", 26379).toString());
//		sentinels.add(new HostAndPort("172.16.34.10", 26379).toString());
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(20);
		poolConfig.setMaxTotal(300);
		poolConfig.setMaxWaitMillis(2000);
		
		JedisSentinelPool sentinelPool = new JedisSentinelPool("cecmaster", sentinels, poolConfig);
		System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
		
		long start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		String dirPerffix = "redis_test/redis-sentinel9/";
		StringBuffer allError = new StringBuffer();
		AtomicInteger seqFlag = new AtomicInteger();
		for (int j = 0; j < 50; j++)
		{
			int size = 10;
			int perNum = 10000;
			CountDownLatch latch = new CountDownLatch(size);// 两个工人的协作
			
			StringBuffer perInfo = new StringBuffer();
			long start2 = System.currentTimeMillis();
			for (int i = 0; i < size; i++)
			{
				String fileName = dirPerffix + j + "/" + i + "/";
				perInfo.append(j+"_"+i+"|||||"+"\r\n");
				new Thread(new MyTask2(latch, sentinelPool, perNum, fileName, seqFlag, perInfo)).start();
			}
			
			try
			{
				latch.await();// 等待所有工人完成工作
				
				long resume = (System.currentTimeMillis() - start2);
				perInfo.append(new Date() + " | 每次调用耗时：" + j + "_" + resume + "\r\n");
				sb.append(new Date() + " | 每次调用耗时：" + j + "_" + resume + "\r\n");
				System.out.println(new Date() + " |  每次调用耗时：" + (resume));
				
				FileUtils.write(new File(dirPerffix + j + "_perInfo.txt"), perInfo.toString());
				allError.append(perInfo.toString());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		System.out.println(new Date() + " | 总调用耗时：" + (System.currentTimeMillis() - start));
		sb.append(new Date() + " | 总调用耗时：" + "all_" + (System.currentTimeMillis() - start) + "\r\n");
		
		try
		{
			FileUtils.write(new File(dirPerffix + "time.txt"), sb.toString());
			FileUtils.write(new File(dirPerffix + "allError.txt"), allError.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		sentinelPool.destroy();
	}
}

class MyTask2 implements Runnable
{
	private CountDownLatch latch;
	private JedisSentinelPool sentinelPool;
	private int perNum;
	private String fileName;
	private AtomicInteger seqFlag;
	private StringBuffer allError;
	public MyTask2(CountDownLatch latch, JedisSentinelPool sentinelPool, int perNum, String fileName,AtomicInteger seqFlag, StringBuffer allError)
	{
		this.latch = latch;
		this.sentinelPool = sentinelPool;
		this.perNum = perNum;
		this.fileName = fileName;
		this.seqFlag = seqFlag;
		this.allError = allError;
	}

	@Override
	public void run()
	{
		String flag = Math.random() * 10000 + "";
		int currentFlag = this.seqFlag.incrementAndGet();
		fileName =  fileName + "_" + flag + "_error";
		int i = 0;
		allError.append(new Date() + "||||" + "线程标识：" + flag + " : ");
		Jedis master = null;
		try
		{
			master = sentinelPool.getResource();
			for (; i < perNum; i++)
			{
				master.set(flag + "_ausername" + i, "test_" + i);
				master.get(flag + "_ausername" + i);
			}
			Set<String> set = master.keys(flag + "_*");
			
			int size = set.size();
			System.out.println("线程标识：" + flag + " : " + size);
			allError.append(new Date() + "||||" + "线程标识：" + flag + " : " + size + "\r\n");
			
			if (size != perNum)  // 插入数量是否正确
			{
				try
				{
					String content = new StringBuilder().append("发生错误----线程标识："+flag+" : "
				            + "顺序标识："+currentFlag+" : " 
							+ "操作总数："+perNum+" : "
							+ "实际插入条数："+size+" : "
							+ set).toString();
					allError.append(content+"\r\n");
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
		}
		catch(Exception ex)
		{
			String content = new StringBuilder().append(new Date() + "||||" + "发生异常----线程标识："+flag+" : "
					+ "顺序标识："+currentFlag+" : "
					+ "已操作序号："+ i +" : "
					+ "操作总数：" + perNum+" : ").toString();
			allError.append(content+"\r\n");
			try
			{
				FileUtils.writeStringToFile(new File(fileName), content);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally
		{
			if (master != null)
				master.close();
			latch.countDown();
		}
	}

}