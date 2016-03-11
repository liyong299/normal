package com.mopon.util.enums;

/**
 * 信息模板Key
 * @author mengxiangrui
 *
 */
public enum TemplateKey {
	/**
	 * 选座票短信
	 */
	SendSeatVoucher("SendVoucherSeat","选座票下发模板"),
	/**
	 * 通兑票短信
	 */
	SendCommonVoucher("SendVoucherCommon","通兑票下发模板"),
	/**
	 *  兑换券短信
	 */
	SendExchangeVoucher("SendExchangeTicket","兑换券下发模板"),
	
	/**
	 * 选座票失败短信
	 */
	SendErrorSeatVoucher("VoucherFail","出票失败短信通知模板");

	private TemplateKey(String val,String name){
		this.val = val;
		this.name = name;
	}

	private String val;
	private String name;
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
