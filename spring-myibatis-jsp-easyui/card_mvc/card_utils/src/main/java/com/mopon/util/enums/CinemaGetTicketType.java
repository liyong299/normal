/**
 * 
 */
package com.mopon.util.enums;

/**
 * 影院表中 取票方式 0:平台凭证号 1：接入商取票号 2：平台凭证号和接入商取票号
 * @author moponyf00111
 *
 */
public enum CinemaGetTicketType {
	VOUCHERNO(0,"平台凭证号"),
	THIRDTICKETCODE(1,"接入商取票号"),
	VOUCHERNOANDTHIRDTICKETCODE(2,"平台凭证号和接入商取票号");
	
	private CinemaGetTicketType(int val, String name) {
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