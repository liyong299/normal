package com.ly.java.thrift.interfaceAnno;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;


public class Test
{

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException
	{
		InterfaceDemo.class.getClassLoader().loadClass(InterfaceDemo.class.getName());

		Constructor[] list = InterfaceDemo.class.getConstructors();

		System.out.println(list.length);
		for (Constructor tmp : list) {
			System.out.println(tmp);
		}
		System.out.println(list);

		Object proxy = Proxy.newProxyInstance(InterfaceDemo.class.getClassLoader(),
				new Class[] { InterfaceDemo.class },
				new MyProxy());

		System.out.println(proxy);
	}
}
