/**
 * 项目名称：java
 * 文件包名：com.ly.java.state
 * 文件名称：OrderState.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月12日 下午4:53:07
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.state;

/**
 * @功能描述：
 * @文件名称：OrderState.java
 * @author ly
 */
public abstract class OrderState implements State {

	@Override
	public Object handler(Order order) {
		if (guardContition(order)) {
			handler0(order);
			handlerAfter(order);
			return order;
		}
		throw new RuntimeException("状态不符合，不能处理。当前状态是：" + order.getStatus() + "，当前状态机是：" + getClass());
	}

	public abstract Object handler0(Order order);

	@Override
	public void handlerAfter(Order order) {
		System.out.println("处理后的状态时：" + order.getStatus());
		handlerAfter0(order);
	}

	public abstract void handlerAfter0(Order order);
}
