/**
 * 
 */
package com.ly.java.thread.volatile1;

import java.util.concurrent.TimeUnit;

/**
 * 没有用volatile的错误,JDK版本低于1.7才会有（未验证哈哈）
 * 
 * @author ly
 * 
 */
public class NoUseVolatileError {

	private static boolean stop;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			public void run() {
				int i = 0;
				while (!stop) {
					i++;
					try {
						System.out.println(i);
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		t1.start();
		TimeUnit.SECONDS.sleep(3);
		stop = true;
	}

}
