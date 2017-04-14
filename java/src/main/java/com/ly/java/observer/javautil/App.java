/**
 * 项目名称：java
 * 文件包名：com.ly.java.observer.javautil
 * 文件名称：App.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月14日 上午10:52:36
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.observer.javautil;


/**
 * @功能描述：
 * @文件名称：App.java
 * @author ly
 */
public class App {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ConcreteSubject observer = new ConcreteSubject();
		observer.addObserver(new ConcreteObserverA());
		observer.addObserver(new ConcreteObserverB());
		observer.change("lili");
	}

}
