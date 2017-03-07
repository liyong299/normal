/**
 * 项目名称：core
 * 文件包名：com.dup.test.util
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月16日 下午4:17:12
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.JsonObject;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：Test.java
 * @author ly
 */
public class Test
{
	@JSONField
	private String name;
	
	/**
	 * <p>功能描述：<p>方法功能</p></p>
	 * <p>实现逻辑：<p>实现步骤</p></p>
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String content = FileUtils.readFileToString(new File("aaaBBaa.txt"));
		JSONArray ob = JSONArray.parseArray(content);
		JSONObject jsonObject = ob.getJSONObject(0);
		System.out.println(jsonObject.get("flag"));
		
	}

}
