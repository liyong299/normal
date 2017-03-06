/**
 * 项目名称：java
 * 文件包名：com.ly.java.other
 * 文件名称：TestForHashCode.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月6日 上午9:37:39
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.other;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @功能描述：
 * @文件名称：TestForHashCode.java
 * @author ly
 */
public class TestForHashCode {

	public static void hashCodeForRemoteIp() throws UnknownHostException {
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		System.out.println(InetAddress.getLocalHost().getHostAddress().hashCode());
	}
	
	/**
	 * 
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		hashCodeForRemoteIp();
	}

}
