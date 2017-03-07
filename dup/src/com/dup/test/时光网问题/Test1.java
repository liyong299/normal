/**
 * 项目名称：core
 * 文件包名：com.dup.test.时光网问题
 * 文件名称：Test1.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月11日 下午3:55:23
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.时光网问题;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ly
 *
 */
public class Test1
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String logPath = "E:\\01_work\\02_cec\\03_需求\\03_幸福蓝海\\02_间隔高峰问题\\209-all_sg.log";
		String codePath = "E:\\01_work\\02_cec\\03_需求\\03_幸福蓝海\\02_间隔高峰问题\\channelcode.txt";
		List<String> logContent2 = FileUtils.readLines(new File(logPath));
		List<String> channelCodes = FileUtils.readLines(new File(codePath));
		Map<String, Integer> map = new HashMap<String, Integer>();
		
//		System.out.println(channelCodes);

		for (String logContent : logContent2) // [请求参数：{"channelCode":["C10000008"],"channelShowCode":["151007720317"],"sign":["e76c4c63468be0a186be69133ba96494"]}]
		{
			if (logContent.contains("请求参数"))
			{
				int startIdx = logContent.indexOf("channelShowCode");
				int midIdx = logContent.indexOf(":");
				int endIdx = logContent.indexOf("\"],\"sign");
				logContent = logContent.substring(startIdx, endIdx).substring(midIdx - 1);
//				System.out.println(logContent);

				if (map.containsKey(logContent))
				{
					Integer num = map.get(logContent);
					if (num == null)
					{
						map.put(logContent, 1);
					}
					else
					{
						map.put(logContent, num + 1);
					}
				}
				else
				{
					map.put(logContent, 1);
				}
//				return;
			}
		}
		
		String logPath2 = "E:\\01_work\\02_cec\\03_需求\\03_幸福蓝海\\02_间隔高峰问题\\2015-12-11-parse.txt";
		FileUtils.write(new File(logPath2), JSONObject.toJSONString(map));
	}

}
