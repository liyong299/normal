package com.ly.java.observer;

public class App {


	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();
		subject.attach(new ConcreteObserverA());
		subject.attach(new ConcreteObserverB());

		subject.change(OrderStateEnum.LOCKED);
		subject.change(OrderStateEnum.PAIED);
	}

}
