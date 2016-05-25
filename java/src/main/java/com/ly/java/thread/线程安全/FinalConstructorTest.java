package com.ly.java.thread.线程安全;

import java.util.ArrayList;
import java.util.List;

public class FinalConstructorTest {

	static abstract class A {

		public A() {
			display();
		}

		public abstract void display();
	}

	static class B extends A {

		private int INT = 100;

		private final int FINAL_INT = 100;

		private final Integer FINAL_INTEGER = 100;

		private String STR1 = "abc";

		private final String FINAL_STR1 = "abc";

		private final String FINAL_STR2 = new String("abc");

		private final List<String> FINAL_LIST = new ArrayList<String>();

		public B() {
			super();
			System.out.println("abc");
		}

		public void display() {
			System.out.println("INT = 100      " + INT);
			System.out.println("final int FINAL_INT = 100      " + FINAL_INT);
			System.out.println("final Integer FINAL_INTEGER = 100      " + FINAL_INTEGER);
			System.out.println("String STR1 = abc      " + STR1);
			System.out.println("final String FINAL_STR1 = abc      " + FINAL_STR1);
			System.out.println("final String FINAL_STR2 = new String(abc)      " + FINAL_STR2);
			System.out.println("final List<String> FINAL_LIST = new ArrayList<String>()      " + FINAL_LIST);
		}
	}

	public static void main(String []args) {
		new B();
	}
}