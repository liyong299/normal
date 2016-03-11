/**
 * 
 */
package com.mopon.util.entity;


/**
 * <p>Description: 影厅类 </p>
 * @date 2015年4月14日
 * @author Aaron
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Hall {

	public Hall() {
	}
	/**
	 * 影听编码
	 */
	private String code;
	
	/**
	 * 影听名称
	 */
	private String name;
	
	/**
	 *座位数量
	 */
	private int seatCount;
	/**
	 *
	 */
	private String cinemaCode;
	/**
	 *
	 */
	private String seatmd5;
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
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	@Override
	public String toString() {
		return "Hall [code=" + code + ", name=" + name + ", seatCount="
				+ seatCount + ", cinemaCode=" + cinemaCode + "]";
	}
	public String getSeatmd5() {
		return seatmd5;
	}
	public void setSeatmd5(String seatmd5) {
		this.seatmd5 = seatmd5;
	}
	
}
