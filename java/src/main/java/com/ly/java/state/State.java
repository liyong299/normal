/**
 * 项目名称：java
 * 文件包名：com.ly.java.state
 * 文件名称：OrderState.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月12日 下午3:25:48
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.state;

/**
 * @功能描述： 定义一个接口以封装与Context的一个特定状态相关的行为；
 * @文件名称：OrderState.java
 * @author ly
 */
public interface State {
	public boolean guardContition(Order order);

	public Object handler(Order order);

	public void handlerAfter(Order order);
}
