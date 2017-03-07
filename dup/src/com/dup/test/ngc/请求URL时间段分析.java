/**
 * 项目名称：core
 * 文件包名：com.dup.test.ngc
 * 文件名称：请求URL分析.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月29日 下午3:19:27
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.ngc;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：请求URL分析.java
 * @author ly
 */
public class 请求URL时间段分析 {

    /**
     * <p>功能描述：<p>方法功能</p></p>
     * <p>实现逻辑：<p>实现步骤</p></p>
     * @param args
     */
    public static void main(String[] args) {
//	String path = "queryshows_00002_21.log";
//	String path = "queryshows_00013_21.log";
	String path = "last_resume.log";
	new 请求URL时间段分析().execute(path);
    }
    
    String prefixjson = "http://10.100.161.24:8080/api/ticket/v1/queryShows.json?";
    String prefixml = "http://10.100.161.24:8080/api/ticket/v1/queryShows.xml?";
    String wgetCommand = "wget -O /home/cec/tmp/{0} ";
    public void execute(String path)
    {
	List<String> fileContents = readFile(path);
	SortedMap<String, Integer> map = new TreeMap<String, Integer>();
	for (String line : fileContents)
	{
//	    if (line.contains("请求参数："))
//	    {
		int flagIndex = line.indexOf("请求地址：");
		String channelCode = line.substring(flagIndex - 7, flagIndex - 2);
		String time = line.substring(0, 16);
		
		String key = time + "_" + channelCode;
//		String key = time;
		if (map.containsKey(key))
		{
		    map.put(key, map.get(key) + 1);
		}
		else
		{
		    map.put(key, 1);
		}
		
//	    }
	}
	
	for (Map.Entry<String, Integer> entry : map.entrySet())
	{
	    System.out.println(entry.getKey() + "     " + entry.getValue());
	}
    }

    public List<String> readFile(String path)
    {
	try {
	    return FileUtils.readLines(new File(path));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
