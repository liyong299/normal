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

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @功能描述：封装请求对象
 * @文件名称：MyHttpRequest.java
 * @author ly
 */
public class MyHttpRequest extends DefaultHttpRequest {

	private Map<String, List<String>> postmap = new TreeMap<>();

	public MyHttpRequest(HttpRequest orgRequest) {
		super(orgRequest.getProtocolVersion(), orgRequest.getMethod(), orgRequest.getUri());
	}

	public MyHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri) {
		super(httpVersion, method, uri);
	}

	public List<String> getParameterValue(String name) {
		List<String> values = postmap.get(name);
		if (values != null) {
			return values;
		}
		return null;
	}

	public Map<String, List<String>> getParameterValues() {
		return postmap;
	}

	public void offer(String key, List<String> values) {
		this.postmap.put(key, values);
	}

	public void offer(String key, String value) {
		List<String> values = this.postmap.get(key);
		if (values == null) {
			values = new ArrayList<String>();
		}
		values.add(value);
		offer(key, values);
	}
}
