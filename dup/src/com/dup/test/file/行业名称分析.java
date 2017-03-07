/**
 * 项目名称：core
 * 文件包名：com.dup.test.file
 * 文件名称：行业名称分析.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月13日 下午2:47:21
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author ly
 * 
 */
public class 行业名称分析
{

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		List<String> lines = FileUtils.readLines(new File("E:/04_personal/03_stack/A股板块分类龙头大全.txt"));
//		for (String line : lines)
//		{
//			if (line.matches(".*[\\d]{1,2}.*"))
//				System.out.println(line.substring(line.indexOf(".") + 1).replaceAll("：", ""));
//		}
		
		for (int i = 0; i < lines.size(); i++)
		{
			String line = lines.get(i);
			if (line.matches(".*[\\d]{1,2}.*"))
			{
				System.out.print(line.substring(line.indexOf(".") + 1).replaceAll("：", "") + "    \t");
				System.out.println(lines.get(++i));
			}
		}
	}

}
