package com.ly.java.thrift;

/**
 * 为了BeanShell测试使用
 * 
 * @author ly
 * 
 */
public class MyBeanShellTest {

	public int add(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {
		System.out.println(new MyBeanShellTest().add(1, 2));
	}

}
