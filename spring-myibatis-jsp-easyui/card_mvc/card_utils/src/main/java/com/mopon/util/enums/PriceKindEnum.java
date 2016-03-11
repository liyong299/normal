package com.mopon.util.enums;

/**
 * 定价方式枚举
 * @author mengxiangrui
 *
 */

public enum PriceKindEnum {
	/**
	 * 固定价
	 */
	FixedPrice(0,"固定价"),
	/**
	 * 固定加价
	 */
	MarkUpPrice(1,"固定加价"),
	/**
	 * 折扣价
	 */
	DiscountPrice(2,"折扣价");
	
	private PriceKindEnum(int val,String name){
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
	
}
