/**
 * 
 */
package com.mopon.util.entity;

/**
 * 
 * <p>Description:票</p>
 * @date 2015年4月14日
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Ticket {

	/**
	 * 影票编码
	 */
	private String ticketCode;
	/**
	 *　	座位编码
	 */
	private String seatCode;
	/**
	 * 座位分组编码
	 */
	private String seatGroupCode;
	/**
	 * 行号
	 */
	private String rowNum;
	/**
	 *　	列号
	 */
	private String colNum;
	/**
	 * 　票价
	 */
	private double price;
	/**
	 * 　服务费
	 */
	private double serviceFee;
	/**
	 * 打印状态(0：未打票，1：已打票)
	 */
	private String printStatus;
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public String getSeatGroupCode() {
		return seatGroupCode;
	}
	public void setSeatGroupCode(String seatGroupCode) {
		this.seatGroupCode = seatGroupCode;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getColNum() {
		return colNum;
	}
	public void setColNum(String colNum) {
		this.colNum = colNum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	
}
