/**
 * 
 */
package com.dup.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 会发生死锁情况。 拿起左边叉子，如果右边叉子不能拿起，则放下。也会发生死锁。
 * 
 * @author hugoyang
 * 
 */
public class PhilosopherProblem2 {

	Fork[] forks = new Fork[5];
	{
		forks[0] = new Fork(1, 0);
		forks[1] = new Fork(2, 0);
		forks[2] = new Fork(3, 0);
		forks[3] = new Fork(4, 0);
		forks[4] = new Fork(5, 0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PhilosopherProblem2 pro = new PhilosopherProblem2();

		pro.new Philosopher(1).start();
		pro.new Philosopher(2).start();
		pro.new Philosopher(3).start();
		pro.new Philosopher(4).start();
		pro.new Philosopher(5).start();
	}

	static AtomicInteger stat = new AtomicInteger();

	class Philosopher extends Thread {

		private int seq;

		public Philosopher(int seq) {
			this.seq = seq;
		}

		private Fork takeFork(int idx) {
			Fork fork = forks[(idx - 1) % 5];
			return fork;
		}

		public void run() {

			while (stat.intValue() < 10000) {
				stat.incrementAndGet();
				System.out.println("philosopher [" + seq + "], i'm think");
				Fork leftFork = takeFork(seq);
				synchronized (leftFork) {
					System.out.println("philosopher [" + seq
							+ "], i toke the [" + leftFork.toString()
							+ "] fork");
					leftFork.setStat(1);

					Fork rightFork = takeFork(seq + 1);

					if (rightFork.getStat() == 0) {
						synchronized (rightFork) {
							rightFork.setStat(1);
							System.out.println("philosopher [" + seq
									+ "], i toke the [" + rightFork.toString()
									+ "] fork");
							System.out.println("philosopher [" + seq
									+ "], i'm eat ");
							try {
								Thread.currentThread().sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							rightFork.setStat(0);
						}
					}
					leftFork.setStat(0);
				}
			}
		}
	}
}

class Fork {
	private int idx;
	private int stat;

	public Fork(int idx, int stat) {
		this.idx = idx;
		this.stat = stat;
	}

	public String toString() {
		return " idx:[" + idx + "] ";
	}

	/**
	 * @return the idx
	 */
	public int getIdx() {
		return idx;
	}

	/**
	 * @param idx
	 *            the idx to set
	 */
	public void setIdx(int idx) {
		this.idx = idx;
	}

	/**
	 * @return the stat
	 */
	public int getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(int stat) {
		this.stat = stat;
	}

}
