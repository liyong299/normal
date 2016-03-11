/**
 * 
 */
package com.mopon.util.enums;

/**
 * 凭证状态
 * @author moponyf00111
 *
 */
public enum VoucherStatus {


	// 
	UNUSED(1,"未使用"),
	USED(2,"已使用"),
	USECOMPLETE(3,"使用完"),
	FROZEN(-1,"已冻结"),
	CANCEL(-2,"作废");
	private VoucherStatus(int val, String name) {
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