/**
 * 项目名称：java
 * 文件包名：com.ly.java.thrift.netty5
 * 文件名称：MyHttpRequest.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年1月10日 下午2:28:14
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thrift.netty5;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * @功能描述：封装请求对象
 * @文件名称：MyHttpRequest.java
 * @author ly
 */
public class MyHttpResponse extends DefaultFullHttpResponse {

	public MyHttpResponse(HttpVersion version, HttpResponseStatus status) {
		super(version, status);
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
