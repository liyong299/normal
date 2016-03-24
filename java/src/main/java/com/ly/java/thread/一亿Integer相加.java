/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread
 * 文件名称：一亿Integer相加.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月8日 下午5:51:26
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：一亿Integer相加.java
 * @author ly
 */
public class 一亿Integer相加
{

	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
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
