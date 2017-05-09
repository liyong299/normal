/**
 * 
 */
package com.ly.java.other;

/**
 * 初始化顺序的问题
 * 
 * @author ly
 * 
 */
public class TestForInitOrder2 {

	private String str = "base";

	public TestForInitOrder2() {
		print();
	}

	public void print() {
		System.out.println(str);
	}

	static class Sub1 extends TestForInitOrder2 {
		private String str = "sub";

		public void print() {
			System.out.println(str);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Sub1();
	}
}
