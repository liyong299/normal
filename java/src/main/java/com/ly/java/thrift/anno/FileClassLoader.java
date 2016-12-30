/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.load
 * 文件名称：FileClassLoader.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月19日 下午2:50:16
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thrift.anno;

import java.io.File;
import java.io.IOException;

import org.aspectj.util.FileUtil;

import sun.misc.VM;

/**
 * 类加载工具类
 * @author ly
 * 
 */
@SuppressWarnings("restriction")
public class FileClassLoader extends ClassLoader
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	
	/**
	 * true if the name is null or has the potential to be a valid binary name
	 * 该方法拷贝自ClassLoader，检查类名是否正确
	 * 
	 * @param name
	 * @return
	 */
	private boolean checkName(String name)
	{
		if ((name == null) || (name.length() == 0))
			return true;
		boolean allowArraySyntax = VM.allowArraySyntax();
		if ((name.indexOf('/') != -1) || (!allowArraySyntax && (name.charAt(0) == '[')))
			return false;
		return true;
	}

	/**
	 * 根据文件径和类名，加载一个class
	 * 
	 * @param file
	 * @throws IOException
	 */
	public Class load(String clsName, File file) throws IOException
	{
		if (!checkName(clsName))
		{
			return null;
		}
		Class c = findLoadedClass(clsName);
		if (c == null)
		{
			byte[] clsData = FileUtil.readAsByteArray(file);
			return this.defineClass(clsName, clsData, 0, clsData.length);
		}
		return c;
	}

	/**
	 * 根据文件路径和类名，加载一个class
	 * 
	 * @param file
	 * @throws IOException
	 */
	public Class load(String clsName, String filePath) throws IOException
	{
		return load(clsName, new File(filePath));
	}


	/**
	 * 如果当前类加载器和父类加载器中都不存在这个类，则跑出异常。
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	@Override
	protected Class<?> findClass(String clsName) throws ClassNotFoundException
	{
		Class c = findLoadedClass(clsName);
		if (c == null)
		{
			throw new ClassNotFoundException("类不存在：" + clsName);
		}
		return c;
	}

}
