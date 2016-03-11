/**
 * 
 */
package com.mopon.util.entity;

/**
 * <p>Description: 座位类</p>
 * @date 2015年4月13日
 * @author Aaron
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Seat {

	public Seat() {
	}
	
	/**
	 * 座位编码
	 */
	private String code;
	/**
	 * 分组编码
	 */
	private String groupCode;
	/**
	 * 行号
	 */
	private String rowNum;
	/**
	 * 列号编码
	 */
	private String colNum;
	/**
	 * 横做标
	 */
	private int xcoord;
	/**
	 * 竖做标
	 */
	private int ycoord;
	/**
	 * 1普通 2：情侣
	 */
	private int type;
	/**
	 * 可用状态0不可用，1可用
	 */
	private int status;
	/**
	 * 情侣做编码
	 */
	private String loveCode;
	/**
	 */
	private String cinemaCode;
	/**
	 */
	private String hallCode;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLoveCode() {
		return loveCode;
	}
	public void setLoveCode(String loveCode) {
		this.loveCode = loveCode;
	}
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	public String getHallCode() {
		return hallCode;
	}
	public void setHallCode(String hallCode) {
		this.hallCode = hallCode;
	}
	public int getXcoord() {
		return xcoord;
	}
	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}
	public int getYcoord() {
		return ycoord;
	}
	public void setYcoord(int ycoord) {
		this.ycoord = ycoord;
	}
	@Override
	public String toString() {
		return "Seat [code=" + code + ", groupCode=" + groupCode + ", rowNum="
				+ rowNum + ", colNum=" + colNum + ", xcoord=" + xcoord
				+ ", ycoord=" + ycoord + ", type=" + type + ", status="
				+ status + ", loveCode=" + loveCode + ", cinemaCode="
				+ cinemaCode + ", hallCode=" + hallCode + "]";
	}
	
}
