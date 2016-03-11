/**
 * 
 */
package com.mopon.util.enums;

/**
 * 订单详情里的 goodsTyper
 * @author moponyf00111
 *
 */
public enum GoodsType {


	//订单详情:商品类型 1：选座票 2：单家通兑票 3：多家通兑票4:兑换券
	SELECT(1,"选座票"),
	SINGLE(2,"单家通兑票"),
	MULTIPLY(3,"多家通兑票"),
	EXCHANG(4,"兑换券");
	private GoodsType(int val, String name) {
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