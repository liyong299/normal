/**
 * 项目名称：core
 * 文件包名：com.dup.test.http
 * 文件名称：测试速度.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月15日 下午3:20:56
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.http;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

/**
 * @author ly
 * 
 */
public class 测试速度
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++)
		{
			Connection.Response res = null;
			try
			{
				res = Jsoup.connect("http://www.baidu.com")
				//						  .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
						.data("query", "Java") // 请求参数  
						.userAgent("I ’ m jsoup") // 设置 User-Agent   
						.cookie("auth", "token") // 设置 cookie   
						.timeout(3000) // 设置连接超时时间  
						//						  .method(Method.GET)
						.execute();
				//				System.out.println(res.body());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}
