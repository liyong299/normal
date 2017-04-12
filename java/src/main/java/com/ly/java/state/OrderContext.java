/**
 * 项目名称：java
 * 文件包名：com.ly.java.state
 * 文件名称：OrderContext.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月12日 下午3:27:28
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.state;

/**
 * @功能描述：定义客户感兴趣的接口。维护一个ConcreteState子类的实例，这个实例定义当前状态；
 * @文件名称：OrderContext.java
 * @author ly
 */
public class OrderContext {
	private State state;
	private Order order;


	public void request() {
		state = OrderStateFactory.getState(order);

		state.handler(order);
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}


		/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}


		/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

}
