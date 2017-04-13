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
public class OrderStateFactory {

	public static OrderState getState(Order order) {
		if (order == null || order.getStatus() == null) {
			throw new RuntimeException("对象错误，不能生成状态机");
		}
		switch (order.getStatus()) {
		case LOCKED:
			return new StateLocked();
		case PAIED:
			return new StatePaied();
		case SUCCESS:
			return new StateSuccess();
		default:
			throw new RuntimeException("状态错误，不能生成状态机");
		}
	}
}
