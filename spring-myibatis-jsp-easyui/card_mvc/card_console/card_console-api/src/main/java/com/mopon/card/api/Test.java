/**
 * 项目名称：cec.api
 * 文件包名：cn.mopon.cec.api
 * 文件名称：Test.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月1日 下午2:18:23
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.mopon.card.api;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;

/**
 * @author ly
 *
 */
public class Test
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		Converter<byte[], Object> deserializer = new DeserializingConverter();
		byte[] source = FileUtils.readFileToByteArray(new File("D:/Work/Workspace/scec_dis/spring/aa.txt"));
		System.out.println(deserializer.convert(source));
	}

}
