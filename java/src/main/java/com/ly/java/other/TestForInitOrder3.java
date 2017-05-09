/**
 * 
 */
package com.ly.java.other;

/**
 * 对象的初始化顺序 @ 1、父类的静态变量、静态代码块、父类的域变量\代码块、子类的静态变量、静态代码块、子类的域变量\代码块
 * 
 * @author ly
 * 
 */
public class TestForInitOrder3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CB();
	}

}

class CA {
	{
		System.out.println("CA CLASS ");
	}
	static {
		System.out.println("CA Static ");
	}
}

class CB extends CA {
	{
		System.out.println("CB CLASS ");
	}
	static {
		System.out.println("CB Static ");
	}
}
