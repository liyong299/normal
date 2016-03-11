package com.mopon.entity.common;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 应用程序上下文
 * @author chenyu
 *
 */
public class Context {
	/*
	 * 请求对象
	 */
	private HttpServletRequest request = null;
	
	/*
	 * 响应对象
	 */
	private HttpServletResponse response = null;
	
	/*
	 * cookie集合
	 */
	private HashMap<String, String> cookies = null;
	
	/*
	 * 访问mvc控制器名称
	 */
	private String controller = null;
	
	/*
	 * 访问mvc方法名称
	 */
	private String action = null;
	
	/*
	 * 获取请求对象
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	/*
	 * 设置请求对象
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/*
	 * 获取响应对象
	 */
	public HttpServletResponse getResponse() {
		return response;
	}
	/*
	 * 设置响应对象
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	/*
	 * 获取cookie
	 */
	public String getCookie(String key) {
		String cookie = cookies.get(key);
		if(cookie == null){
			return "";
		}
		else{
			return cookie;
		}
	}
	/*
	 * 设置cookies
	 */
	public void setCookies(HashMap<String, String> cookies) {
		this.cookies = cookies;
	}
	
	/**
	 * 访问mvc控制器名称
	 * @return
	 */
	public String getController() {
		return controller;
	}
	/**
	 * 访问mvc控制器名称
	 * @return
	 */
	public void setController(String controller) {
		this.controller = controller;
	}
	
	/**
	 * 访问mvc方法名称
	 * @return
	 */
	public String getAction() {
		return action;
	}
	/**
	 * 访问mvc方法名称
	 * @return
	 */
	public void setAction(String action) {
		this.action = action;
	}
}