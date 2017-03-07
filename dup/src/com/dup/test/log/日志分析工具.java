/**
 * 
 */
package com.dup.test.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * @author hugoyang
 *
 */
public class 日志分析工具 
{
	private String perLogStart = "2015-10-28";
	/**
	 * 分页读取文件
	 * @param filePath
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	private List<String> readFile(String filePath, int startLine, int endLine)
	{
		Reader reader = null;
		BufferedReader br = null;
		try {
			reader = new FileReader(new File(filePath));
			br = new BufferedReader(reader);
			int count=0;
			String str;
			List<String> contents = new ArrayList<String>();
		     while((str=br.readLine()) != null)  // TODO 需要优化，不能每次都从第一行开始读取
		     {
		    	 if(count < startLine  && !str.contains(perLogStart)) // 保证这一次读取的第一行是一个完整的日志条目
		         {
		        	 continue;
		         }
		         if(count>=startLine||count<endLine)
		         {
		        	 contents.add(str);
		         }
		         else if (count>endLine && !str.contains(perLogStart))  // 保证这一次读取的最后一行是一个完整的日志条目
		         {
		        	 contents.add(str);
		         }
		         else if (count>endLine && str.contains(perLogStart)) 
		         {
		             return contents;
		         }
		         count++;
		    }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(reader);
		}
		return null;
	}
	
	private List<LogBean> parseLogPage(List<String> logContents)
	{
		if (logContents == null || logContents.size() == 0)
		{
			System.out.println("【parseLogPage】参数为空。");
			return null;
		}
		
		LogBean logBean = new LogBean();
		boolean isOver = false;
		for (String logString : logContents)
		{
			if (logString.contains(perLogStart)) // 一个完整日志条目的开始
			{
				String[] arr = logString.split(" ");
				
			}
		}
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
