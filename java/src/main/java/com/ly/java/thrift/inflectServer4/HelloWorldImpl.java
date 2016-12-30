package com.ly.java.thrift.inflectServer4;

import org.apache.thrift.TException;

import com.ly.java.thrift.anno.SAASServerMap;

/** 
 * HelloWord 接口实现类 
 * 
 */
@SAASServerMap("/demo/order")
public class HelloWorldImpl {

	@SAASServerMap("/add")
	public String add(String username) throws TException {
		System.out.println(Thread.currentThread().getName() + " HelloWorldImpl.sayHello ----  "
				+ System.currentTimeMillis());
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello world, " + username + "-" + System.currentTimeMillis();
	}

}