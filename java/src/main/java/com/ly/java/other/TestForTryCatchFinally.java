/**
 * 
 */
package com.ly.java.other;

/**
 * @author ly
 * 
 */
public class TestForTryCatchFinally {

	public static int test() {
		try {
			return 1;
		} catch (Exception ex) {
			return 2;
		} finally {
			return 3;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(TestForTryCatchFinally.test());
	}

}
