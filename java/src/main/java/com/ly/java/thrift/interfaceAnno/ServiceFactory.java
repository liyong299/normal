/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.aop.java测试
 * 文件名称：ServiceFactory.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月27日 上午11:34:01
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thrift.interfaceAnno;

/**
 * @author ly
 * 
 */
public class ServiceFactory
{
	public static void before()
	{
		System.out.println("前置日记：打印、启动事务等..");
	}

	public static void after()
	{
		System.out.println("后置日记：打印、关闭事务等..");
	}

	public static void other()
	{
		System.out.println("做其他的事..");
	}
}
