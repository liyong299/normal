package com.dup.test.数据库;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {

	private String prefix;
	private static AtomicInteger threadNumber = new AtomicInteger(1);

	public MyThreadFactory(String prefix) {
		this.prefix = prefix;
	}
	@Override
	public Thread newThread(Runnable r) {
		final Thread thread = new Thread(r, "thread_" + prefix + "_" + threadNumber.incrementAndGet());
		return thread;
	}

}
