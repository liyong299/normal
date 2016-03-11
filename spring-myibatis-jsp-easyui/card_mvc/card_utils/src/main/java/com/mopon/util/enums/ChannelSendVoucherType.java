/**
 * 
 */
package com.mopon.util.enums;

/**
 * 凭证下发方式
 * @author moponyf00111
 *
 */
public enum ChannelSendVoucherType {
    NOTSEND(0,"不下发"),
	SENDSMS(1,"短信"),
	SENDMMS(2,"彩信"),
	WHCHAT(3,"微信");
	private ChannelSendVoucherType(int val, String name) {
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