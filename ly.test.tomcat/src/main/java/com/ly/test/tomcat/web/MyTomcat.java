/**
 * 项目名称：ly.test.tomcat
 * 文件包名：com.ly.test.tomcat.web
 * 文件名称：MyTomcat.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月22日 下午3:10:39
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.tomcat.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ly
 *
 */
public class MyTomcat
{
	private ServerSocket serverSocket;
	
	// 监听端口
	private int port;
	
	private boolean isAlive = true;
	
	public MyTomcat(int port)
	{
		this.port = port;
	}
	
	public void startup()
	{
		try
		{
			serverSocket = new ServerSocket(port);
			System.out.println("tomcat 启动开始");
			while(isAlive)
			{
				// 等待客户端连接
				Socket socket = serverSocket.accept();
				
				// 启动线程，将客户端socket交给请求分发器处理
				new Thread(new DispathRequest(socket)).start();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// 启动tomcat
		new MyTomcat(6378).startup();
	}

}
