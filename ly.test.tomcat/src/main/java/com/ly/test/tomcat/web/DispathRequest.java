/**
 * 项目名称：ly.test.tomcat
 * 文件包名：com.ly.test.tomcat.web
 * 文件名称：ClientRequest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月22日 下午3:30:57
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.tomcat.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * @author ly
 * 
 */
public class DispathRequest implements Runnable
{
	private Socket socket;

	public DispathRequest(Socket socket)
	{
		super();
		this.socket = socket;
	}

	public void run()
	{
		System.out.println("=======接收到请求=======");
		InputStream in = null;
		OutputStream out = null;
		try
		{
			in = socket.getInputStream();
			
			System.out.println("按照http协议解析客户端数据...");

			HttpServletRequest request ;
			
			out = socket.getOutputStream();
			System.out.println("流长度：" + socket.getReceiveBufferSize());
			byte[] b = new byte[socket.getReceiveBufferSize()];
			in.read(b);
			System.out.println("读取结束");
			System.out.println(new String(b));
			
			System.out.println("=======处理并输出请求=======");
			out.write("<html><title>欢迎访问MyTomcat</title><head></head><body>欢迎访问MyTomcat</body></html>".getBytes());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					in = null;
				}
			}
			if (null != out)
			{
				try
				{
					out.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					out = null;
				}
			}
		}

	}
}
