/**
 * 
 */
package com.mopon.util.enums;

/**
 * 商品详情里的 ticket_type
 * @author moponyf00111
 *
 */
public enum CinemaCommSale {


	// 
	START(1,"启售"),
	STOP(0,"停售");
	
	private CinemaCommSale(int val, String name) {
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