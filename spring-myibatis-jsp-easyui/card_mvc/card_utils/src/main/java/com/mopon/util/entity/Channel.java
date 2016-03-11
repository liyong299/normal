/**
 * 
 */
package com.mopon.util.entity;

/**
 * <p>Description: </p>
 * @date 21 Apr 2015
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Channel {

	private String code;
	private String name;
	/**
	 * 通讯密钥
	 */
	private String secKey;
	/**
	 * 渠道类型（1：分销 2：自有）
	 */
	private String type;
	/**
	 * 开放状态（1：开放 0：关闭）
	 */
	private int opened;
	/**
	 * 销售状态（1：开放 0：关闭）
	 */
	private int salable;
	
	private String md5;
 
	@Override
	public String toString() {
		return "Channel [code=" + code + ", name=" + name + ", secKey="
				+ secKey + ", type=" + type + ", opened=" + opened
				+ ", salable=" + salable + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecKey() {
		return secKey;
	}

	public void setSecKey(String secKey) {
		this.secKey = secKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOpened() {
		return opened;
	}

	public void setOpened(int opened) {
		this.opened = opened;
	}

	public int getSalable() {
		return salable;
	}

	public void setSalable(int salable) {
		this.salable = salable;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
 
}
