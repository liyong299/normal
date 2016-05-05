package com.ly.java.concurrent.synchronize;

public 
class WorkObj {
    private int x = 0;

    public synchronized void test1() {
	for (int i = 0; i < 10; i++) {
	    try {
		Thread.currentThread().sleep(500);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    System.out.println(Thread.currentThread().getName() + "   " + getX());
	}

    }

    public synchronized void test2() {
	for (int i = 0; i < 10; i++) {
	    try {
		Thread.currentThread().sleep(400);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    setX(i);
	    System.out.println(Thread.currentThread().getName() + "   " + getX());
	}
    }

    /**
     * @return the x
     */
    public int getX() {
	return x;
    }

    /**
     * @param x
     *            the x to set
     */
    public void setX(int x) {
	this.x = x;
    }
}