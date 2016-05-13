/**
 * 项目名称：java
 * 文件包名：com.ly.java.file
 * 文件名称：逐行读入文件.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月9日 下午7:39:24
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：逐行读入文件.java
 * @author ly
 */
public class 逐行读入文件 {

    /**
     * <p>功能描述：<p>方法功能</p></p>
     * <p>实现逻辑：<p>实现步骤</p></p>
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
//	test获取关键字();
	test获取关键字上下文();
    }

    public static void test获取关键字() throws IOException
    {
	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("e:\\mvc.log")), "UTF-8"));
	String line = null;
	while((line = br.readLine()) != null)
	{
//	    System.out.println(line);
	    if (line.matches(".*queryCinemasTest_0.*执行时间.*")) 
	    {
		String[] arr = line.split(" ");
		int spendTime = Integer.parseInt(arr[1]);
		if (spendTime > 50000)
		{
		    System.out.println(line);
		}
	    }
	}
	br.close();
    }
    
    public static void test获取关键字上下文() throws IOException
    {
	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("e:\\mvc-20160509.log")), "UTF-8"));
	String line = null;
	int range = 30, beforeFlag = 1, afterFlag = 0;
	String[] afterStrArr = new String[range]; 
	boolean isContain = false;
	String matchLine = null, key = "0.12659082122436693][执行时间： 112063 毫秒";
	Queue<String> beforeQueue = new ArrayBlockingQueue<String>(range);
	
	while((line = br.readLine()) != null)
	{
	    isContain = isContain ? isContain : line.contains(key); // 如果已经匹配到了就不在更新标志的值
	    if (!isContain)
	    {
		if (beforeFlag >= range)
		{
		    beforeQueue.remove();  // 移除队列中先进入的
		}
		beforeQueue.add(line);     // 加入队列
		beforeFlag++;
	    }
	    else
	    {
		matchLine = matchLine == null ? line : matchLine;
//		System.out.println(afterFlag);
		if (afterFlag < range)
		{
		    afterStrArr[afterFlag++] = line;
		}
		else
		    break;
	    }
	}
	br.close();
	
	Object[] beforeStrArr = beforeQueue.toArray();
	for (int i = 0; i < beforeStrArr.length; i++)
	{
	    System.out.println(beforeStrArr[i]);
	}
	System.out.println("---------------------");
	System.out.println(matchLine);
	for (int i = 0; i < afterStrArr.length; i++)
	{
	    System.out.println(afterStrArr[i]);
	}
    }
}
