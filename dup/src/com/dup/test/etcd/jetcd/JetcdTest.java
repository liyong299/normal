/**
 * 项目名称：dup
 * 文件包名：com.dup.test.etcd.jetcd
 * 文件名称：JetcdTest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月8日 下午3:28:02
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.etcd.jetcd;


/**
 * @author ly
 *
 */
public class JetcdTest
{
	/**
	 * @param args
	 * @throws EtcdException 
	 */
	public static void main(String[] args) throws EtcdException
	{
		String serverUrl = "http://172.16.34.12:2379";
		EtcdClient client = EtcdClientFactory.newInstance(serverUrl);
		client.set("hello", "world");
		System.out.println(client.get("hello"));
		client.delete("hello");
	}

}
