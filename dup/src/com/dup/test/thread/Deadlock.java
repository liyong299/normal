/**
 * 
 */
package com.dup.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hugoyang
 * 
 */
public class Deadlock {

	public static StringBuilder res1 = new StringBuilder();
	public static StringBuilder res2 = new StringBuilder();

	static AtomicInteger idx = new AtomicInteger();

	class T1 extends Thread {
		public void run() {

			while (idx.intValue() < 100) {
				synchronized (res1) {

					res2.append(idx.incrementAndGet());
					System.out.println(Thread.currentThread().getName() + "_"
							+ res2);
				}
			}
		}
	}

	class T2 extends Thread {
		public void run() {
			while (idx.intValue() < 100) {
				synchronized (res2) {

					res1.append(idx.incrementAndGet());
					System.out.println(Thread.currentThread().getName() + "_"
							+ res1);
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deadlock dl = new Deadlock();
		dl.new T1().start();
		dl.new T2().start();
	}

}
