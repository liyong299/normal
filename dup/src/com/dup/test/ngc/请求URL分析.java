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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：请求URL分析.java
 * @author ly
 */
public class 请求URL分析 {

    /**
     * <p>功能描述：<p>方法功能</p></p>
     * <p>实现逻辑：<p>实现步骤</p></p>
     * @param args
     */
    public static void main(String[] args) {
//	String path = "queryshows_00002_21.log";
//	String path = "queryshows_00013_21.log";
	String path = "queryshow_21_all.log";
	new 请求URL分析().execute(path);
    }
    
    String prefixjson = "http://10.100.161.24:8080/api/ticket/v1/queryShows.json?";
    String prefixml = "http://10.100.161.24:8080/api/ticket/v1/queryShows.xml?";
    String wgetCommand = "wget -O /home/cec/tmp/{0} ";
    public void execute(String path)
    {
	List<String> fileContents = readFile(path);
	List<String> allUrlParams = new ArrayList<String>();
	
	for (String line : fileContents)
	{
	    if (line.contains("请求参数："))
	    {
		int channelCodeIndx = line.indexOf("channelCode");
		int cinemaCodeIndx = line.indexOf("cinemaCode");
		String channelCode = line.substring(channelCodeIndx + 15, channelCodeIndx + 20);
		String cinemaCode = line.substring(cinemaCodeIndx + 15, cinemaCodeIndx + 22);
		String key = channelCode + "_" + cinemaCode;
		if (allUrlParams.contains(key))
		{
		    continue;
		}
		else
		{
		    allUrlParams.add(key);
		}
		String command = wgetCommand + "\"" + prefixjson + line.replaceAll("\\[请求参数：\\{\"", "")
			.replaceAll("\":\\[\"", "=").replaceAll("\"\\],\"", "&")
			.replaceAll("\"\\]\\}\\]", "") + "\"";
		System.out.println(MessageFormat.format(command, new Object[]{key}));
	    }
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
