/**
 * 项目名称：excel
 * 文件包名：excel
 * 文件名称：Test3.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月17日 下午2:53:35
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package excel;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

import com.ly.test.util.DateStyle;
import com.ly.test.util.DateUtil;

/**
 * @author ly
 *
 */
public class Test3
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String filePath = "E:/01_work/02_cec/03_需求/04_NGC/03_过年期间NG链接问题/cata_1.log";
		Map<String, String[]> content = readSrc(filePath);
		
		String filePath2 = "E:/01_work/02_cec/03_需求/04_NGC/03_过年期间NG链接问题/cata_2.log";
		Map<String, String[]> content2 = readSrc(filePath2);
		
		for (Map.Entry<String, String[]> entry : content.entrySet())
		{
			if (!content2.containsKey(entry.getKey()))
			{
				System.out.println(entry.getValue()[0]);
			}
		}
		
		for (Map.Entry<String, String[]> entry : content2.entrySet())
		{
			if (!content.containsKey(entry.getKey()))
			{
				System.out.println(entry.getValue()[0]);
			}
		}
	}

	private static Map<String, String[]> readSrc(String srcFile)
	{
		// 读取每个机器上shell分析结果的内容
		Map<String, String[]> content = new TreeMap<String, String[]>();
		try
		{
			List<String> list = FileUtils.readLines(new File(srcFile));
			for (String line : list)
			{
				StringTokenizer st = new StringTokenizer(line, "  ");
				String date = st.nextToken();
				String min = st.nextToken();
				
				content.put(date + "" + min, new String[] {line});
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return content;
	}
}
