package com.ly.java.observer;

public class ConcreteObserverA implements AbstractObserver {

	@Override
	public void update(Object obj, Subject subject) {
		System.out.println("观察者A收到消息：" + obj);
	}
}
