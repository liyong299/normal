/**
 * 项目名称：java
 * 文件包名：com.ly.java.observer.javautil
 * 文件名称：ConcreteObserver.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月14日 上午10:50:22
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.observer.javautil;

import java.util.Observable;
import java.util.Observer;

/**
 * @功能描述：
 * @文件名称：ConcreteObserver.java
 * @author ly
 */
public class ConcreteObserverA implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("观察者A发现被观察者变化为：" + arg);
	}

}
