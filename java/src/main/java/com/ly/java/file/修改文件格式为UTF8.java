/**
 * 项目名称：java
 * 文件包名：com.ly.java.file
 * 文件名称：修改文件各位.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月12日 上午9:19:57
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.file;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 功能描述：<p color="red">并不能百分之百有效</p>
 * 文件名称：修改文件各位.java
 * @author ly
 */
public class 修改文件格式为UTF8 {

    /**
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
	String rootPath = "D:\\Work\\Workspace\\other\\java\\java\\src\\main\\java\\com\\ly";
	修改文件格式根据目录(rootPath);
    }
    
    static List<File> files = new ArrayList<File>();
    public static void readDir(String rootPath)
    {
	File file = new File(rootPath);
	if (file.isDirectory())
	{
	    File[] subFiles = file.listFiles();
	    for (File perFile : subFiles)
	    {
		readDir(perFile.getAbsolutePath());
	    }
	}
	else
	{
	    System.out.println(rootPath);
	    files.add(file);
	}
    }
    
    public static void 修改文件格式根据目录(String rootPath) throws Exception
    {
	readDir(rootPath);
	修改文件格式所有的();
    }
    
    public static void 修改文件格式所有的() throws Exception
    {
	for (File file : files)
	{
	    修改文件格式(file);
	}
    }

    public static void 修改文件格式(File file) throws Exception
    {
	String code = new 判断文件格式().guessFileEncoding(file);
	if (code.contains("UTF-8"))
	{
	    return;
	}
	System.out.println(file.getAbsolutePath() + "  编码格式为 ：  " + code);
	String fileContent = FileUtils.readFileToString(file, Charset.forName(code));
	
	byte[] tmp = fileContent.getBytes(code);
	byte[] newTmp = new String(tmp, code).getBytes("UTF-8");
	String convertContent = new String(newTmp, "UTF-8");
	System.out.println(convertContent.substring(0, 100));
	
//	FileUtils.write(file, convertContent, "UTF-8");
    }
}
