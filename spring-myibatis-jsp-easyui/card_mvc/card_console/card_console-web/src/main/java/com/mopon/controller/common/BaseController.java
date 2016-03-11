package com.mopon.controller.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mopon.interceptor.ApplicationContext;
import com.mopon.util.Page;

public class BaseController<T> {
	private static final Logger LOGGER = Logger.getLogger(BaseController.class);
	
	/**
	 * 
	 * 方法用途: <br>
	 * @param request
	 * @param params 实体类
	 * @param page  分页类
	 */
	protected void setParams(HttpServletRequest request,T params,Page<T> page){
		page.setUrl(request.getRequestURI());
		if (page.getPage() < 1) {
			page.setPage(1);
		}
		Class<? extends Object> bean = params.getClass();
		Field[] fields = bean.getDeclaredFields();
		for(Field field:fields){
			try {
				if(!"serialVersionUID".equals(field.getName())){
					String methodName = "get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1, field.getName().length());
					Method method = bean.getDeclaredMethod(methodName);
					Object fieldValue = method.invoke(params);
					page.getParams().put(field.getName(),fieldValue);
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error(e.getMessage());
			} catch (IllegalAccessException e) {
				LOGGER.error(e.getMessage());
			} catch (NoSuchMethodException e) {
				LOGGER.error(e.getMessage());
			} catch (SecurityException e) {
				LOGGER.error(e.getMessage());
			} catch (InvocationTargetException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	/**
	 * 获取用户ID
	 * @return
	 */
	public Integer getUserID() {
		return Integer.parseInt(ApplicationContext.cookie("userID") !="" ? ApplicationContext.cookie("userID"):"-1");
	}

	/**
	 * 获取角色ID
	 * @return
	 */
	public Integer getRoleID() {
		return Integer.parseInt(ApplicationContext.cookie("roleID") !="" ? ApplicationContext.cookie("roleID") : "-1");
	}
	
	/**
	 * 获取用户名称
	 * @return
	 */
	public String getFullName() {
		String  fullName =  ApplicationContext.cookie("fullName");
		try {
			fullName = URLDecoder.decode(ApplicationContext.cookie("fullName"), "utf-8");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return fullName;
	}
}
