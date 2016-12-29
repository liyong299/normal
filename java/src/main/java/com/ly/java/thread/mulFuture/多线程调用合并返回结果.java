/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.mulFuture
 * 文件名称：多线程调用合并返回结果.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年12月23日 下午4:29:36
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.mulFuture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @功能描述：
 * @文件名称：多线程调用合并返回结果.java
 * @author ly
 */
public class 多线程调用合并返回结果 {

	/**
	 * 
	 * @param args
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		List<MyTask> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				tasks.add(new MyTask2(i));
			} else {
				tasks.add(new MyTask3(i));
			}
		}
		List<Future<Map>> result = Executors.newFixedThreadPool(10).invokeAll(tasks);
		if (result != null) {
			Map<String, String> map = new HashMap<>(30);
			for (Future<Map> future : result) {
				map.putAll(future.get());
			}
			System.out.println(map);
		}
	}
}

class MyTask implements Callable<Map> {

	private int idx;

	public MyTask(int i) {
		idx = i;
	}
	@Override
	public Map call() throws Exception {
		Map map = new HashMap<String, String>(1);
		if (idx % 2 == 0) {
			map.put("key偶数_" + idx, "value_" + idx);
		} else {
			map.put("key奇数_" + idx, "value_" + idx);
		}

		return map;
	}
}

class MyTask2 extends MyTask {

	private int idx;

	public MyTask2(int i) {
		super(1);
		idx = i;
	}

	@Override
	public Map call() throws Exception {
		Map map = new HashMap<String, String>(1);
		if (idx % 2 == 0) {
			map.put("key2偶数_" + idx, "value_" + idx);
		} else {
			map.put("key2奇数_" + idx, "value_" + idx);
		}

		return map;
	}
}

class MyTask3 extends MyTask {

	private int idx;

	public MyTask3(int i) {
		super(1);
		idx = i;
	}

	@Override
	public Map call() throws Exception {
		Map map = new HashMap<String, String>(1);
		if (idx % 2 == 0) {
			map.put("key3偶数_" + idx, "value_" + idx);
		} else {
			map.put("key3奇数_" + idx, "value_" + idx);
		}

		return map;
	}
}