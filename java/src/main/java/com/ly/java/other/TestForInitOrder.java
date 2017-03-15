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
public class TestForInitOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Z();
	}

}

class A {
	public A() {
		System.out.println("A");
	}

	public A(String flag) {
		System.out.println(flag);
	}
}

class B {
	A a = new A("X");

	public B() {
		System.out.println("B");
	}

	{
		System.out.println("B2");
	}
	A a2 = new A("Y");
}

class Z extends B {
	A a = new A();

	public Z() {
		System.out.println("Z");
	}
}