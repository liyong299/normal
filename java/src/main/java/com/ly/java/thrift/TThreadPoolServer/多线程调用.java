package com.ly.java.thrift.TThreadPoolServer;

public class 多线程调用 {

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new MyTask(i).start();
		}
	}

}

class MyTask extends Thread {
	private int idx;

	public MyTask(int i) {
		this.idx = i;
	}
	public void run() {
		System.out.println(Thread.currentThread().getName() + " --  " + System.currentTimeMillis());
		new HelloClient().startClient(idx + "");
	}
}