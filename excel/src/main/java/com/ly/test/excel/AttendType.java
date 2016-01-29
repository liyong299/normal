/**
 * 项目名称：excel
 * 文件包名：com.ly.test.excel
 * 文件名称：AttendType.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年1月25日 下午4:20:48
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.excel;

/**
 * @author ly
 * 
 */
public enum AttendType
{
	zhengchang(0, "正常"), kuanggong(1, "旷工"), chiDao(2, "迟到"), chiDaohuanchong(4, "迟到缓冲"), shangBanUnknown(9, "上班时间未知"), xiaBanUnknown(10, "下班时间未知");
	private int value;

	private String text;

	AttendType(int value, String text)
	{
		this.value = value;
		this.text = text;
	}

	public int getValue()
	{
		return value;
	}

	public String getText()
	{
		return text;
	}
}
