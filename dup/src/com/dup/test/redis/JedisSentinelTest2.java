package com.dup.test.redis;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelTest2
{
	public static void main(String[] args) throws InterruptedException
	{
		Set sentinels = new HashSet();
		sentinels.add(new HostAndPort("172.16.10.131", 26378).toString());
		sentinels.add(new HostAndPort("172.16.10.133", 26378).toString());
		sentinels.add(new HostAndPort("172.16.10.134", 26378).toString());
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(20);
		poolConfig.setMaxTotal(60);
		poolConfig.setMaxWaitMillis(2000);
		
		JedisSentinelPool sentinelPool = new JedisSentinelPool("scec", sentinels, poolConfig);
		
		System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
		
		Jedis master = sentinelPool.getResource();

		master.set("test_1", "aaaa");
		master.set("test_3", "aaaa");
		master.set("t11a", "t1_value");
		master.expire("t11a", 3);
		
//		sentinelPool.returnResource(master);;
		
		while (true) {
			Set<String> set = master.keys("*");
			System.out.println(set);
			if (!set.contains("t11a")) {
				break;
			}
			TimeUnit.SECONDS.sleep(1);
		}
		
		
		master.close();
		sentinelPool.destroy();
	}
}
