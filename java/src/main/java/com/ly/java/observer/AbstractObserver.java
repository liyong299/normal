/**
 * 项目名称：java
 * 文件包名：com.ly.java.observer
 * 文件名称：Subject.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月13日 下午4:17:00
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.observer;


/**
 * @功能描述：抽象主题角色类
 * @文件名称：Subject.java
 * @author ly
 */
public interface AbstractObserver {

	public abstract void update(Object obj, Subject subject);
}
