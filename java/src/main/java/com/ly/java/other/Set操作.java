/**
 * 项目名称：java
 * 文件包名：com.ly.java.other
 * 文件名称：Set操作.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月10日 下午8:32:12
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.other;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：Set操作.java
 * @author ly
 */
public class Set操作 {

    /**
     * <p>功能描述：<p>方法功能</p></p>
     * <p>实现逻辑：<p>实现步骤</p></p>
     * @param args
     */
    public static void main(String[] args) {
	test移除2();
    }

    public static void test移除2()
    {
	Set<String> set = new HashSet<String>();
	for (int i = 0; i < 1000; i++)
	{
	    set.add("i" + i);
	}
	
	Iterator<String> iterator = set.iterator();
	while(iterator.hasNext())
	{
	    if (Math.random() * 100 > 50)
	    {
		System.out.println(iterator.next());
		iterator.remove();
	    }
	}
    }
    
    public static void test移除()
    {
	Set<String> set = new HashSet<String>();
	for (int i = 0; i < 1000; i++)
	{
	    set.add("i" + i);
	}
	
	for (String str : set)
	{
	    if (Math.random() * 100 > 50)
	    {
		set.remove(str);
	    }
	}
    }
}
