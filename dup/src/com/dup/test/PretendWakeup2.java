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
public class PretendWakeup2 {

	static volatile int idx = 0;
	static StringBuffer sb = new StringBuffer();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyStack2 stack = new MyStack2();
		PushTask2 task = new PushTask2(stack);
		PopTask2 task2 = new PopTask2(stack);

		new Thread(task).start();
		// new Thread(task).start();
		new Thread(task2).start();
		new Thread(task2).start();
		new Thread(task2).start();
		new Thread(task2).start();

		try {
			Thread.currentThread().sleep(1000);
			System.out.println(sb);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

class PushTask2 implements Runnable {
	MyStack2 stack;

	public PushTask2(MyStack2 stack) {
		this.stack = stack;
	}

//	@Override
	public void run() {
		while (PretendWakeup2.idx < 1000) {
			// System.out.println(Thread.currentThread().getName() + ",  "
			// + PretendWakeup.idx);
			PretendWakeup2.sb.append(
					Thread.currentThread().getName() + ",  "
							+ PretendWakeup2.idx).append("\r\n");
			stack.push("" + PretendWakeup2.idx++);
		}
	}
}

class PopTask2 implements Runnable {
	MyStack2 stack;

	public PopTask2(MyStack2 stack) {
		this.stack = stack;
	}

//	@Override
	public void run() {
		while (PretendWakeup2.idx < 1000) {
			try {
				String str = stack.pop();
				// System.out.println(Thread.currentThread().getName() + ",  "
				// + str);
				PretendWakeup2.sb.append(
						Thread.currentThread().getName() + ",  " + str).append(
						"\r\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class MyStack2 {
	private List<String> list = new ArrayList<String>();

	public synchronized void push(String value) {
		synchronized (this) {
			list.add(value);
			notify();
		}
	}

	public synchronized String pop() {
		synchronized (this) {
			if (list.size() <= 0) {
				try {
					wait();
					// System.out.println("等待线程：" +
					// Thread.currentThread().getName());
					PretendWakeup2.sb.append(
							"等待线程：" + Thread.currentThread().getName()).append(
							"\r\n");
				} catch (InterruptedException e) {
					PretendWakeup2.sb.append(
							"等待线程：" + Thread.currentThread().getName()
									+ "            " + e.getLocalizedMessage())
							.append("\r\n");
				}
			}
			// System.out.println("取数线程：" + Thread.currentThread().getName());
			PretendWakeup2.sb
					.append("取数线程：" + Thread.currentThread().getName()).append(
							"\r\n");
			return list.remove(list.size() - 1);
		}
	}
}
