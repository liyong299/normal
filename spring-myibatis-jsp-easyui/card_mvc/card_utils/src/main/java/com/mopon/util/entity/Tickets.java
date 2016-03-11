package com.mopon.util.entity;
public class Tickets {
	public Tickets() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 影票编码
	 */
	private String ticketCode;
	/**
	 * 二维码
	 */
	private String barCode;
	/**
	 * 座位编码
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
	 * 列号
	 */
	private String colNum;
	/**
	 * 票价
	 */
	private Double price;
	/**
	 * 服务费
	 */
	private Double serviceFee;
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
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

}