/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.thread
 * �ļ����ƣ�һ��Integer���.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��3��8�� ����5:51:26
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.thread;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * ����������<p color="red">ʵ�ּ���</p>
 * �ļ����ƣ�һ��Integer���.java
 * @author ly
 */
public class һ��Integer���
{

	/**
	 * <p>����������<p>��������</p></p>
	 * <p>ʵ���߼���<p>ʵ�ֲ���</p></p>
	 * @param args
	 */
	public static void main(String[] args)
	{
		
	}
	
	public static void writeFile()
	{
		File file = new File("tttt.log");
		int count = 10; 
		int per = 1000000000 / count;
		for (int i = 0; i < count; i++)
		{
			new AddTask(i * per, (i + 1) * per, file).start();
		}
	}
}

class AddTask extends Thread
{
	private int start;
	private int end;
	private File file;
	public AddTask(int start, int end, File file)
	{
		this.start = start;
		this.end = end;
		this.file = file;
	}
	
	public void run()
	{
		for (int i = start; i < end; i++)
		{
			try
			{
				FileUtils.write(file, Math.round(1f) * 1000000 + 1000000 + "", true);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
