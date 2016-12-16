package com.ly.java.thread.线程安全;

import com.ly.java.thread.线程安全.FinalConstructorTest.A;

public class 内部类 {
    public void test() {
	    final int a = 100;//这个a必须定义为final，才能被匿名子类直接使用
	    final Integer aa = null;
	    final String bb = null;
	    new A() {
	        public void display() {
				//	            aa = 2;
				//	            bb = "cc";
	            System.out.println(a);
	        }
	    };
	}
}
