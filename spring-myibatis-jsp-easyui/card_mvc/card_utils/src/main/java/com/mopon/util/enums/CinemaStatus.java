/**
 * 
 */
package com.mopon.util.enums;

/**
 * 商品详情里的 ticket_type
 * @author moponyf00111
 *
 */
public enum CinemaStatus {


	// 
	START(1,"启用"),
	STOP(0,"停用");
	
	private CinemaStatus(int val, String name) {
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