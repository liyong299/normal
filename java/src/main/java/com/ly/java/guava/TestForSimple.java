/**
 * 项目名称：java
 * 文件包名：com.ly.java.guava
 * 文件名称：TestForSimple.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月8日 下午6:54:14
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.guava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @功能描述：
 * @文件名称：TestForSimple.java
 * @author ly
 */

public class TestForSimple {

	@Test
	public void TestLoadingCache() throws Exception {

	}

	public static void main(String[] args) throws ExecutionException {
		final LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder()
				.expireAfterWrite(2, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						String strProValue = "hello " + key + "!";
						return strProValue;
					}

				});

		cahceBuilder.put("jerry", "11122");
		System.out.println("jerry value:" + cahceBuilder.get("jerry"));

		cahceBuilder.put("harry", "ssdded");
		System.out.println("harry value:" + cahceBuilder.get("harry"));

		new Thread() {
			@Override
			public void run() {
				int idx = 0;
				String tmpKey = "jerry";
				for (;;) {
					try {
						TimeUnit.MILLISECONDS.sleep(1200);
						System.out.println("peida value:" + cahceBuilder.get(tmpKey));
						cahceBuilder.put("jerry" + idx, "11122" + idx++);
						if (idx > 3) {
							tmpKey = "jerry" + (idx - 2);
						}
					} catch (Exception e) {
						System.out.println("没有拿到！！！！");
						e.printStackTrace();
						break;
					}
				}
			}
		}.start();
	}
}
