/**
 * 
 */
package com.dup.test.oom;

/**
 * @author hugoyang
 * 
 */
public class OMMTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (true) {
			System.out.println(i++);
			sb.append((i % 10)).append("_");
		}
	}

}
