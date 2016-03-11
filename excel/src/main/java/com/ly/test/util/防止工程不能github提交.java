/**
 * 项目名称：excel
 * 文件包名：com.ly.test.util
 * 文件名称：防止工程不能github提交.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月27日 下午4:35:51
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author ly
 *
 */
public class 防止工程不能github提交
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String projectDirPath = "D:/Work/Workspace/other/java/spring-myibatis-jsp-easyui";
		emptyDirAddFile(projectDirPath);
	}

	private static void emptyDirAddFile(String projectDirPath) throws IOException
	{
		File projectDir = new File(projectDirPath);
		
		if (projectDir.isDirectory())
		{
			File[] files = projectDir.listFiles();
			if (files.length == 0)
			{
				System.out.println(projectDirPath);
				FileUtils.write(new File(projectDirPath + "/新建文件aa.txt"), "暂无作用");
				return ;
			}
			for (File file : files)
			{
				if (file.isDirectory())
				{
					emptyDirAddFile(file.getAbsolutePath());
				}
			}
		}
	}
}
