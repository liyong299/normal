/**
 * 项目名称：core
 * 文件包名：com.dup.test.时光网问题
 * 文件名称：Test2.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月14日 上午10:52:59
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.时光网问题;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONArray;

/**
 * @author ly
 *
 */
public class Test4
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String parse1 = "E:\\01_work\\02_cec\\03_需求\\03_幸福蓝海\\02_间隔高峰问题\\2015-12-11-parse.txt";
		String parse2 = "E:\\01_work\\02_cec\\03_需求\\03_幸福蓝海\\02_间隔高峰问题\\2015-12-14-parse.txt";
		
		String content1 = FileUtils.readFileToString(new File(parse1));
		String content2 = FileUtils.readFileToString(new File(parse2));
		
		Map<String, Integer> map1 = (Map<String, Integer>) JSONArray.parse(content1);
		Map<String, Integer> map2 = (Map<String, Integer>) JSONArray.parse(content1);
		Map<String, Integer> map3 = (Map<String, Integer>) JSONArray.parse(content1);
		
		Map<Integer, String> map11 = new TreeMap<Integer, String>();
		for (Map.Entry<String, Integer> entry : map2.entrySet())
		{
			String key = entry.getKey();
			Integer val = entry.getValue();
			
			if (map1.containsKey(key))
			{
				
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, String> entry : map11.entrySet())
		{
			sb.append(entry.getKey()).append("=").append(entry.getValue())
			.append(System.getProperty("line.separator"));
		}
//		String logPath1 = "E:\\01_work\\02_cec\\03_需求\\03_幸福蓝海\\02_间隔高峰问题\\2015-12-14-visitcnt.txt";
//		FileUtils.write(new File(logPath1), sb.toString());
	}

}
