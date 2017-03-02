package com.ly.java.thread.testForLock;

public class ModifyReferenceObjThreadByLock extends Thread {
	private SecondObj obj;

	public ModifyReferenceObjThreadByLock(SecondObj obj) {
		this.obj = obj;
	}

	public void run() {
		obj.setParam(Thread.currentThread().getName());
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "  ::  " + obj.getParam());
		}
	}
}