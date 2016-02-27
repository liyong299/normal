/**
 * ��Ŀ���ƣ�ly.test.tomcat
 * �ļ�������com.ly.test.tomcat.web
 * �ļ����ƣ�ClientRequest.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��22�� ����3:30:57
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
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
		System.out.println("=======���յ�����=======");
		InputStream in = null;
		OutputStream out = null;
		try
		{
			in = socket.getInputStream();
			
			System.out.println("����httpЭ������ͻ�������...");

			HttpServletRequest request ;
			
			out = socket.getOutputStream();
			System.out.println("�����ȣ�" + socket.getReceiveBufferSize());
			byte[] b = new byte[socket.getReceiveBufferSize()];
			in.read(b);
			System.out.println("��ȡ����");
			System.out.println(new String(b));
			
			System.out.println("=======�����������=======");
			out.write("<html><title>��ӭ����MyTomcat</title><head></head><body>��ӭ����MyTomcat</body></html>".getBytes());
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
