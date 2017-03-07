/**
 * 项目名称：core
 * 文件包名：com.dup.test.etcd.jetcd
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月8日 下午6:18:45
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.etcd.jetcd;


/**
 * @author ly
 *
 */
public class Test
{

	/**
	 * @param args
	 * @throws EtcdException 
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println(System.getProperties());
		EtcdClientFactory.newInstance();
	}

}
