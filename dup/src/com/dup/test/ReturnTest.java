/**
 * 
 */
package com.dup.test;

/**
 * @author hugoyang
 * 
 */
public class ReturnTest {

	public static String test() {
		try {
			int a = 1 / 0;
			return "try return ";
		} catch (Exception ex) {
			System.out.println("catch block");
			return "catch return ";
		} finally {
			System.out.println("finally block");
			return "finally return";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = test();
		System.out.println(str);
	}

}
