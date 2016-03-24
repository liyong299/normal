/**
 * 项目名称：java
 * 文件包名：com.ly.java.other
 * 文件名称：数组和List互转.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月23日 上午12:04:27
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：数组和List互转.java
 * @author ly
 */
public class 数组和List互转
{

	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
	 * @param args
	 */
	public static void main(String[] args)
	{
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		final int size =  list.size();
		String[] arr = (String[])list.toArray(new String[size]);
		System.out.println(arr[0]);
	}

}
