package com.ly.java.thrift.anno;

import java.lang.reflect.Method;

public class URIBean {
	private Object obj;
	private Method method;

	public URIBean(Object obj, Method method) {
		this.obj = obj;
		this.method = method;
	}

	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}
}