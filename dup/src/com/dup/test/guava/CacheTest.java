/**
 * 
 */
package com.dup.test.guava;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author hugoyang
 * 
 */
public class CacheTest {

	@Test
	public void TestLoadingCache() throws Exception {
		CacheLoader<String, String> loader = new CacheLoader<String, String>() {
			@Override
			public String load(String key) throws Exception {
				String strProValue = "hello " + key + "!";
				return strProValue;
			}
		};

		LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder()
		// 设置并发级别为8，并发级别是指可以同时写缓存的线程数
				.concurrencyLevel(8)
				// 设置写缓存后8秒钟过期
				.expireAfterWrite(8, TimeUnit.SECONDS)
				// 设置缓存容器的初始容量为10
				.initialCapacity(10)
				// 设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
				.maximumSize(100)
				// 设置要统计缓存的命中率
				.recordStats().build(loader);

		System.out.println(cacheBuilder.get("we"));

		TimeUnit.SECONDS.sleep(1);

		System.out.println("jerry value:" + cacheBuilder.get("jerry"));
		System.out.println("peida value:" + cacheBuilder.get("peida"));
		cacheBuilder.put("harry", "ssdded");
		System.out.println("harry value:" + cacheBuilder.get("harry"));

		System.out.println("命中率： " + cacheBuilder.stats().toString());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
