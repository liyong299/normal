package com.dup.test.redis.集群测试;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.dup.test.util.XLSUtils;

public class 集群测试结果分析
{
	public static void main(String[] args) throws Exception
	{
		集群测试结果分析 obj = new 集群测试结果分析();
		
		String dirPath = "E://01_work//02_cec//03_需求//01_院线通//07_redis集群//34.10";
		obj.分析所有文件(dirPath);
		XLSUtils.write("test10r.xls", "34.10", 1, obj.datar);
		XLSUtils.write("test10rs.xls", "34.10", 1, obj.datars);
		
		dirPath = "E://01_work//02_cec//03_需求//01_院线通//07_redis集群//34.9";
		obj.分析所有文件(dirPath);
		XLSUtils.write("test9r.xls", "34.9", 1, obj.datar);
		XLSUtils.write("test9rs.xls", "34.9", 1, obj.datars);
		
		dirPath = "E://01_work//02_cec//03_需求//01_院线通//07_redis集群//34.8";
		obj.分析所有文件(dirPath);
		XLSUtils.write("test8r.xls", "34.8", 1, obj.datar);
		XLSUtils.write("test8rs.xls", "34.8", 1, obj.datars);
	}
	
	private void 分析所有文件(String dirPath) throws Exception
	{
		this.分析所有文件(getFileList(dirPath));
		System.out.println(result);
	}
	
	private File[] getFileList(String dirPath)
	{
		File file = new File(dirPath);
		return file.listFiles();
	}
	private StringBuilder result = new StringBuilder();
	private void 分析所有文件(File[] files) throws IOException
	{
//		System.out.println(files);
		datar = new String[files.length][7];
		datars = new String[files.length][7];
		int idxr = 0;
		int idxrs = 0;
		for (File file : files)
		{
			this.分析所单个文件(file, idxr, idxrs);
			if (file.getName().contains("r.log"))
			{
				datar[idxr][0] = file.getName();
				idxr++;
			}
			
			if (file.getName().contains("rs.log"))
			{
				datars[idxrs][0] = file.getName();
				idxrs++;
			}
		}
		
	}
	
	String[][] datar = null;
	String[][] datars = null;
	private void 分析所单个文件(File file, int idxr, int idxrs) throws IOException
	{
		List<String> lines = FileUtils.readLines(file);
		result.append(file.getName()).append(System.getProperty("line.separator"));
		
		StringBuilder result2 = new StringBuilder();
		StringBuilder result3 = new StringBuilder();
		
		int rdx = 1;
		int rsdx = 1;
		for (String line : lines)
		{
			if (line.contains("requests per second"))
			{
//				result2.append(line).append(System.getProperty("line.separator"));
				int startIdx = line.indexOf(":") + 1;
				int endIdx = line.indexOf("requests per second");
//				result3.append(line.substring(startIdx, endIdx)).append(System.getProperty("line.separator"));
				if (file.getName().contains("r.log"))
				{
					datar[idxr][rdx] = line.substring(startIdx, endIdx);
					rdx++;
				}
				
				if (file.getName().contains("rs.log"))
				{
					datars[idxrs][rsdx] = line.substring(startIdx, endIdx);
					rsdx++;
				}
			}
		}
//		result.append(result3);
//		result.append(result2);
		result2 = null;
		result3 = null;
	}
}
