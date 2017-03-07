/**
 * 
 */
package com.dup.test;

/**
 * @author hugoyang
 * 
 */
public class SisuoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class MyWaitNotify {

	Object myMonitorObject = new Object();

	public void doWait() {
		synchronized (myMonitorObject) {
			try {
				myMonitorObject.wait();
			} catch (InterruptedException e) {
				System.out.println("in dowait InterruptedException");
			}
		}
	}

	public void doNotify() {
		synchronized (myMonitorObject) {
			myMonitorObject.notify();
		}
	}
}