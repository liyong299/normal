package com.ly.java.observer.javautil;

import java.util.Observable;

/**
 * @功能描述：具体的主题，继承java.util.Observable 被观察者
 * @文件名称：ConcreteSubject.java
 * @author ly
 */
public class ConcreteSubject extends Observable {

	private String name;

	public void change(String name) {
		if (name != null && name.equals(this.name)) {
			return;
		}
		if (this.name != null && this.name.equals(name)) {
			return;
		}
		if (this.name == null && name == null) {
			return;
		}
		this.name = name;
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "name=" + name;
	}

}
