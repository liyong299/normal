/**
 * 项   目  名：SCEC
 * 包          名：JedisAPI
 * 文   件  名：JedisTool.java
 * 版本信息：SCEC_Branches
 * 日          期：2015年9月16日-上午9:06:22
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.mopon.card.api.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author LRL
 * @version [SCEC_Branches, 2015年9月16日]
 * @备注：
 */
public class JedisTool {
	private static JedisPool pool = null;

	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	public static JedisPool getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(2048);
			config.setMaxTotal(4096);
			config.setMaxWaitMillis(10000);
			config.setTestOnBorrow(true);
			pool = new JedisPool(config, "172.16.34.48", 6379, 100000);
		}
		return pool;
	}

	/**
	 * 返还到连接池
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) {
		byte[] value = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return value;
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public void set(byte[] key, byte[] value) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
	}
}