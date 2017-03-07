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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONArray;

/**
 * @author ly
 *
 */
public class Test2
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
		Map<String, Integer> map2 = (Map<String, Integer>) JSONArray.parse(content2);
		
		// 判断影院 34052001 在时间段12-14到12-21之间的所有排期是否都请求了一遍。
		String file1 = "E:\\01_work\\02_cec\\03_需求\\03_幸福蓝海\\02_间隔高峰问题\\14-21_chancode.txt";
		List<String> list = FileUtils.readLines(new File(file1));
		for (String code : list)
		{
			if (map2.containsKey(code))
			{
				
			}
			else
			{
				System.out.println("'" + code + "',");
			}
		}
				
		Map<Integer, String> map11 = new TreeMap<Integer, String>();
		for (Map.Entry<String, Integer> entry : map2.entrySet())
		{
			String key = entry.getKey();
			Integer val = entry.getValue();
			if (map11.containsKey(val))
			{
				map11.put(val, map11.get(val) + " | " + key);
			}
			else
			{
				map11.put(val, key);
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
