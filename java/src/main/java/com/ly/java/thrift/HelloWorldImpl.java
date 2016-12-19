package com.ly.java.thrift;

import org.apache.thrift.TException;

import com.ly.java.thrift.anno.SAASServerMap;

/**
 * HelloWord 接口实现类
 * 
 */
@SAASServerMap(uri = "/user")
public class HelloWorldImpl implements HelloWorld.Iface {
	/*
	 * 什么都不返回，观察性能
	 * 
	 * @see com.ly.java.thrift.HelloWorld.Iface#sayHello(java.lang.String)
	 */
	@Override
	@SAASServerMap(uri = "/sayHello")
	public String sayHello(String username) throws TException {
		System.out.println("HelloWorldImpl.sayHello");
		// return "hello world, " + username;
		return username;
	}
}