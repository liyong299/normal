/**
 * 
 */
package com.dup.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 会发生死锁情况。 按规则轮询，必然会有死锁。
 * 
 * @author hugoyang
 * 
 */
public class PhilosopherProblem {

	Integer[] forks = new Integer[] { 1, 2, 3, 4, 5 };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PhilosopherProblem pro = new PhilosopherProblem();

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

		private Integer takeFork(int idx) {
			Integer fork = forks[(idx - 1) % 5];
			return fork;
		}

		public void run() {

			while (stat.intValue() < 1000) {
				stat.incrementAndGet();
				System.out.println("philosopher [" + seq + "], i'm think");
				Integer leftFork = takeFork(seq);
				Integer rightFork = takeFork(seq + 1);
				synchronized (leftFork) {
					System.out.println("philosopher [" + seq
							+ "], i toke the [" + leftFork + "] fork");
					synchronized (rightFork) {

						System.out.println("philosopher [" + seq
								+ "], i toke the [" + rightFork + "] fork");
						System.out.println("philosopher [" + seq
								+ "], i'm eat ");
					}
				}
			}

		}
	}

}
