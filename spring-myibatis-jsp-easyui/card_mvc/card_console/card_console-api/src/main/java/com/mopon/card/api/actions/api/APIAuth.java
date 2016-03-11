/**
 * 项目名称：card_console-api
 * 文件包名：cn.mopon.cec.api.actions.api
 * 文件名称：APIAuth.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年3月7日 下午2:53:50
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.mopon.card.api.actions.api;

/**
 * 功能描述：<p color="red">API接口的权限控制</p>
 * 文件名称：APIAuth.java
 * @author ly
 */
public enum APIAuth
{
	QUERY_USERBYCODE("查询用户", "100");

	private String text;
	private String value;

	/**
	 * 构造方法
	 * 
	 * @param text
	 *            文本
	 * @param value
	 *            值
	 */
	private APIAuth(String text, String value) {
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return text;
	}
}
