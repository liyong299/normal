/**
 * 
 */
package com.dup.test;

/**
 * @author hugoyang
 * 
 */
public class VolicateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyTask task = new MyTask();

		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		Thread t3 = new Thread(task);

		t1.start();
		t2.start();
		t3.start();
	}

}

class MyTask implements Runnable {

	private volatile int a = 0;

//	@Override/
	public void run() {
		while (a < 10) {
			System.out.println(Thread.currentThread().getName() + ",  " + a++);
		}
	}

}