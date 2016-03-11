/**
 * 项目名称：excel
 * 文件包名：excel
 * 文件名称：查找文件中没有响应消息的内容.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月26日 上午11:44:47
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author ly
 *
 */
public class 查找文件中没有响应消息的内容
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String filePath = "E:/01_work/02_cec/03_需求/02_中影/13_中影锁座成功率分析/2016-02-08_hln_lockSeats.log";
		String destFile = "E:/01_work/02_cec/03_需求/02_中影/13_中影锁座成功率分析/aaa.log";
		
		new File(destFile).delete();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		
		String str = null;
		StringBuilder sb = new StringBuilder();
		boolean oneRequesFlag = false;
		while ((str = br.readLine()) != null)
		{
//			System.out.println(str);
			if (str.matches("2016-02-08.*火烈鸟.*调用.*"))  // 一次新的请求开始
			{
//				System.out.println(str);
				String str2 = br.readLine();
				String str3 = br.readLine();
				String str4 = br.readLine();
				if (str4.contains("响应消息"))
				{
					continue;
				}
				else
				{
					sb.append(str).append("\r\n");
					sb.append(str2).append("\r\n");
					sb.append(str3).append("\r\n");
					sb.append(str4).append("\r\n");
					FileUtils.write(new File(destFile), sb.toString(), true);
					sb.delete(0, sb.length());  // 写入文件后，清空临时buffer
				}
			}
		}
		
		br.close();
	}

}
