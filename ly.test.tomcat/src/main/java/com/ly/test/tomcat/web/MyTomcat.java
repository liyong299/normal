/**
 * ��Ŀ���ƣ�ly.test.tomcat
 * �ļ�������com.ly.test.tomcat.web
 * �ļ����ƣ�MyTomcat.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��2��22�� ����3:10:39
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
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
	
	// �����˿�
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
			System.out.println("tomcat ������ʼ");
			while(isAlive)
			{
				// �ȴ��ͻ�������
				Socket socket = serverSocket.accept();
				
				// �����̣߳����ͻ���socket��������ַ�������
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
		// ����tomcat
		new MyTomcat(6378).startup();
	}

}
