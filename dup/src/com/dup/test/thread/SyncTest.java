/**
 * 
 */
package com.dup.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hugoyang
 * 
 */
public class SyncTest implements Runnable {

	static Integer si = 0;
	private static AtomicInteger flag = new AtomicInteger();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec1 = Executors.newCachedThreadPool();
		ExecutorService exec2 = Executors.newCachedThreadPool();
		SyncTest task = new SyncTest();
		SyncTest task2 = new SyncTest();
		exec1.execute(task);
		exec2.execute(task2);

		while (true) {
			System.out.println("flag = " + flag.get());
			if (flag.get() == 2) {
				System.out.println("si = " + si);
			}
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < 200; i++) {
			synchronized (si) {
				si++;
			}
		}
		flag.incrementAndGet();
	}

}
