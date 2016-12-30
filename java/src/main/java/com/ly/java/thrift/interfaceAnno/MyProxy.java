package com.ly.java.thrift.interfaceAnno;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Invocation动态代理实现
 * @author ly
 *
 */
public class MyProxy implements InvocationHandler
{
	/**
	 * 对一个接口类，进行代理
	 * @param delegate
	 * @return
	 */
	public Object bind(Class delegate) {
		// 创建代理对象并返回
		return Proxy.newProxyInstance(delegate.getClassLoader(), delegate.getInterfaces(), this);
	}

	/**
	 * 都需要通过该方法进行动态调用
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		Object obj = null;
		// 执行前置的方法  
        ServiceFactory.before();  
        // 通过反射，执行目标方法，也就是你的主要目的  
		System.out.println("执行调用数据库方法：：：：：");
        // 执行后置的方法  
        ServiceFactory.after();  
        // 返回值给调用者  
		return obj;
	}
}