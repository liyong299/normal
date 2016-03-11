/**
 * 
 */
package com.mopon.util.enums;

/**
 * 销售活动商品里的 goodsType
 * @author moponyf00111
 *
 */
public enum ActiveGoodsType {


	//订单详情:商品类型 0：选座票 1：通兑票
	SELECT(0,"选座票"),
	COMMON(1,"通兑票");
	
	private ActiveGoodsType(int val, String name) {
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