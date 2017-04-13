/**
 * 项目名称：java
 * 文件包名：com.ly.java.observer
 * 文件名称：Subject.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月13日 下午4:17:00
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @功能描述：抽象主题角色类
 * @文件名称：Subject.java
 * @author ly
 */
public abstract class Subject {

	private List<AbstractObserver> list = new ArrayList<>();

	public void attach(AbstractObserver observer) {
		if (list.contains(observer)) {
			return;
		}
		list.add(observer);
		System.out.println("注册观察者成功" + observer);
	}

	public void detach(AbstractObserver observer) {
		list.remove(observer);
		System.out.println("删除观察者成功" + observer);
	}

	public void notifyAllObservers(OrderStateEnum state) {
		for (AbstractObserver observer : list) {
			observer.update(state, this);
		}
	}
}
