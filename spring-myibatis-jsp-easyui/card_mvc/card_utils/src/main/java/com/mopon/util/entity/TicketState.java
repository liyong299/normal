/**
 * 
 */
package com.mopon.util.entity;

import java.util.List;

/**
 * 
 * <p>Description:打票状态</p>
 * @date 2015年4月14日
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class TicketState {

	/**
	 * 影院编码
	 */
	private String cinemaCode;
	/**
	 * 影院名称
	 */
	private String cinemaName;
	/**
	 * 	影厅编码
	 */
	private String hallCode;
	/**
	 * 	影厅名称
	 */
	private String hallName;
	/**
	 * 	影片编码
	 */
	private String filmCode;
	/**
	 * 	影片名称
	 */
	private String filmName;
	/**
	 *	影院场次编码
	 */
	private String cinemaShowCode;
	/**
	 * 	渠道场次编码
	 */
	private String channelShowCode;
	/**
	 * 	放映时间（格式: yyyy-MM-dd hh:mm:ss）
	 */
	private String showTime;
	/**
	 * 	重打印次数
	 */
	private String reprintCount;
	/**
	 * 	地面取票号
	 */
	private String printCode;
	/**
	 * 	取票验证码
	 */
	private String verifyCode;
	
	private List<Tickets> tickets;

	public List<Tickets> getTickets() {
		return tickets;
	}
	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getHallCode() {
		return hallCode;
	}
	public void setHallCode(String hallCode) {
		this.hallCode = hallCode;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getFilmCode() {
		return filmCode;
	}
	public void setFilmCode(String filmCode) {
		this.filmCode = filmCode;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getCinemaShowCode() {
		return cinemaShowCode;
	}
	public void setCinemaShowCode(String cinemaShowCode) {
		this.cinemaShowCode = cinemaShowCode;
	}
	public String getChannelShowCode() {
		return channelShowCode;
	}
	public void setChannelShowCode(String channelShowCode) {
		this.channelShowCode = channelShowCode;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getReprintCount() {
		return reprintCount;
	}
	public void setReprintCount(String reprintCount) {
		this.reprintCount = reprintCount;
	}
	public String getPrintCode() {
		return printCode;
	}
	public void setPrintCode(String printCode) {
		this.printCode = printCode;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
