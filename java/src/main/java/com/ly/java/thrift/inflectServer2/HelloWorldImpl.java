package com.ly.java.thrift.inflectServer2;

import org.apache.thrift.TException;

/** 
 * HelloWord 接口实现类 
 * 
 */
public class HelloWorldImpl implements Iface {

	@Override
	public String sayHello(String username) throws TException {
		System.out.println(Thread.currentThread().getName() + " HelloWorldImpl.sayHello  "
				+ System.currentTimeMillis());
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello world, " + username + "-" + System.currentTimeMillis();
	}
}