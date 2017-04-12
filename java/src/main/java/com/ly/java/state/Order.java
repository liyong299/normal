/**
 * 项目名称：java
 * 文件包名：com.ly.java.state
 * 文件名称：Order.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月12日 下午4:48:45
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.state;

/**
 * @功能描述：
 * @文件名称：Order.java
 * @author ly
 */
public class Order {
	private OrderStateEnum status;

	/**
	 * @return the status
	 */
	public OrderStateEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStateEnum status) {
		this.status = status;
	}

}
