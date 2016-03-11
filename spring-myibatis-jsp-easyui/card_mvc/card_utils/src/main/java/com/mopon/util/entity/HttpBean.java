/**
 * 
 */
package com.mopon.util.entity;

import java.util.List;

/**
 * <p>Description: http请求 返回bean用于接受解析json </p>
 * @date 2015年4月13日
 * @author Aaron
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class HttpBean {

	/**
	 * 错误代码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String msg;
	/**
	 * 平台订单号
	 */
	private String orderCode;
	/**
	 * 锁座时长（分钟）
	 */
	private String lockTime;
	/**
	 * 短信类型（1：地面取票号；2：平台凭证号；3：混合方式）
	 */
	private String printMode;
	/**
	 * 接入商类型（1：火烈鸟 2：鼎新 3：火凤凰 4：满天星）
	 */
	private String provider;
	/**
	 * 	凭证号
	 */
	private String voucherCode;
	/**
	 * 取票号
	 */
	private String printCode;
	/**
	 * 	取票验证码
	 */
	private String verifyCode;
	/**
	 * 影院
	 */
	private Cinema cinema;
	/**
	 * 影片
	 */
	private Film film;
	/**
	 * 场次
	 */
	private Show show;
	/**
	 * 订单
	 */
	private Order order;
	/**
	 * 票
	 */
	private Ticket ticket;
	/**
	 * 影院列表
	 */
	private List<Cinema> cinemas;
	/**
	 * 影厅列表
	 */
	private List<Hall> halls;
	/**
	 * 座位列表
	 */
	private List<Seat> seats;
	/**
	 * 影片列表
	 */
	private List<Film> films;
	/**
	 * 场次列表
	 */
	private List<Show> shows;
	/**
	 * 
	 */
	private List<Channel> channels;
	
	/**
	 * 影票列表
	 */
	private List<Tickets> tickets;
	
	/**
	 * 影票列表
	 */
	private List<Snacks> snacks;
	
	public List<Snacks> getSnacks() {
		return snacks;
	}
	public void setSnacks(List<Snacks> snacks) {
		this.snacks = snacks;
	}
	public List<Tickets> getTickets() {
		return tickets;
	}
	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}
	/////////打印票状态start//////////
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
	/////////打印票状态end/////////
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Cinema> getCinemas() {
		return cinemas;
	}
	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	public List<Hall> getHalls() {
		return halls;
	}
	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}
	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	public List<Film> getFilms() {
		return films;
	}
	public void setFilms(List<Film> films) {
		this.films = films;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public List<Show> getShows() {
		return shows;
	}
	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getLockTime() {
		return lockTime;
	}
	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}
	public String getPrintMode() {
		return printMode;
	}
	public void setPrintMode(String printMode) {
		this.printMode = printMode;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getVoucherCode() {
		return voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
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
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "HttpBean [code=" + code + ", msg=" + msg + ", orderCode="
				+ orderCode + ", lockTime=" + lockTime + ", printMode="
				+ printMode + ", provider=" + provider + ", voucherCode="
				+ voucherCode + ", printCode=" + printCode + ", verifyCode="
				+ verifyCode + ", cinema=" + cinema + ", film=" + film
				+ ", show=" + show + ", order=" + order + ", ticket=" + ticket
				+ ", cinemas=" + cinemas + ", halls=" + halls + ", seats="
				+ seats + ", films=" + films + ", shows=" + shows+ ",channels="+channels
				+ ", cinemaCode=" + cinemaCode + ", cinemaName=" + cinemaName
				+ ", hallCode=" + hallCode + ", hallName=" + hallName
				+ ", filmCode=" + filmCode + ", filmName=" + filmName
				+ ", cinemaShowCode=" + cinemaShowCode + ", channelShowCode="
				+ channelShowCode + ", showTime=" + showTime
				+ ", reprintCount=" + reprintCount + "]";
	}
	public List<Channel> getChannels() {
		return channels;
	}
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
}
