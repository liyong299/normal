/**
 * 
 */
package com.dup.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hugoyang
 * 
 */
public class PretendWakeup {

	static volatile int idx = 0;
	static StringBuffer sb = new StringBuffer();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyStack stack = new MyStack();
		PushTask task = new PushTask(stack);
		PopTask task2 = new PopTask(stack);

		new Thread(task).start();
		// new Thread(task).start();
		new Thread(task2).start();
		new Thread(task2).start();
		new Thread(task2).start();
		new Thread(task2).start();

	}

}

class PushTask implements Runnable {
	MyStack stack;

	public PushTask(MyStack stack) {
		this.stack = stack;
	}

	public void run() {
		while (PretendWakeup.idx < 1000) {
			System.out.println(Thread.currentThread().getName() + ",  "
					+ PretendWakeup.idx);
			stack.push("" + PretendWakeup.idx++);
		}
	}
}

class PopTask implements Runnable {
	MyStack stack;

	public PopTask(MyStack stack) {
		this.stack = stack;
	}

//	@Override
	public void run() {
		while (PretendWakeup.idx < 1000) {
			try {
				String str = stack.pop();
				System.out.println(Thread.currentThread().getName() + ",  "
						+ str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class MyStack {
	private List<String> list = new ArrayList<String>();

	public synchronized void push(String value) {
		synchronized (this) {
			list.add(value);
			notifyAll();
		}
	}

	public synchronized String pop() throws InterruptedException {
		synchronized (this) {
			while (list.size() <= 0) {
				wait();
				System.out.println("等待线程：" + Thread.currentThread().getName());

			}
			System.out.println("取数线程：" + Thread.currentThread().getName());

			return list.remove(list.size() - 1);
		}
	}
}
