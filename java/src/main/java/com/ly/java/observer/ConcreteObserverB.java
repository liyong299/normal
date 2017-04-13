package com.ly.java.observer;

public class ConcreteObserverB implements AbstractObserver {

	@Override
	public void update(Object obj, Subject subject) {
		System.out.println("观察者B收到消息：" + obj);
	}
}
