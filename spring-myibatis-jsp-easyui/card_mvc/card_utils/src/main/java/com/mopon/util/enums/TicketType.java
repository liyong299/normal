/**
 * 
 */
package com.mopon.util.enums;

/**
 * 商品详情里的 ticket_type
 * @author moponyf00111
 *
 */
public enum TicketType {


	//商品详情:通兑票类型 1：单家通兑票 2：多家通兑票
	SINGLE(1,"单家通兑票"),
	MULTIPLY(2,"多家通兑票");
	
	private TicketType(int val, String name) {
		this.val = val;
		this.name = name;
	}
	
	private int val;

	private String name;

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}