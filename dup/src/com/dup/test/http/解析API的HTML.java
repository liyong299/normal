/**
 * 项目名称：scec-saas-server
 * 文件包名：com.mopon.cec
 * 文件名称：解析API的HTML.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年10月8日 下午7:49:11
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.http;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @功能描述：解析API转换的HTML文档
 * @文件名称：解析API的HTML.java
 * @author ly
 */
public class 解析API的HTML {

	/**
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws IOException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		List list = new ArrayList();
		String content = FileUtils.readFileToString(new File("cookie.txt"));
		Object obj = JSONObject.parse(content);
		if (obj instanceof JSONArray) {
			List<JSONObject> array = (List<JSONObject>) obj;
			for (JSONObject tmp : array) {
				list.add(tmp.toJavaObject(tmp, TestA.class));
			}
		}
		System.out.println(list);
	}

}
