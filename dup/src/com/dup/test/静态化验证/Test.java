/**
 * 项目名称：dup
 * 文件包名：com.dup.test.静态化验证
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年11月21日 下午9:48:37
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.静态化验证;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.DateUtils;
import org.joda.time.DateTime;

/**
 * @author ly
 *
 */
public class Test
{
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException
	{
		
	}
	
	public static void test() throws IOException
	{
		String filePath = "E:\\show7\\api\\ticket\\v1\\queryShows\\0001\\1003972\\2015-11-21_all.json";
		String fileC1 = FileUtils.readFileToString(new File(filePath));
		
		String filePath2 = "E:\\show7\\api\\ticket\\v1\\queryShows\\0001\\1003972\\2015-11-21_all1.json";
		String fileC2 = FileUtils.readFileToString(new File(filePath2));
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++)
		{
			File file1 = new File("e://tt.xtx");
			FileUtils.writeStringToFile(file1, fileC1);
			
			
			File file2 = new File("e://tttt.xtx");
			FileUtils.writeStringToFile(file2, fileC2+ i);
			
			FileUtils.copyFile(file2, file1);
			file2.delete();
		}
		System.out.println(System.currentTimeMillis() - start);
		
		
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++)
		{
			File file1 = new File("e://tt.xtx");
			FileUtils.writeStringToFile(file1, fileC1);
			
			
			File file2 = new File("e://tttt.xtx");
			FileUtils.writeStringToFile(file2, fileC2 + i);
			
			file1.delete();
			file2.renameTo(file1);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
