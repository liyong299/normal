package com.mopon.entity;
/**
 * 信息模板中的关键字实体
 * @author mengxiangrui
 *
 */
public class BaseBaseTempKeyWord {
	/**
	 * 凭证号
	 */
	private String voucherno;
	/**
	 * 商品名称
	 */
	private String goodsname;
	/**
	 * 商品数量
	 */
	private String goodsnum;
	/**
	 * 有效期止
	 */
	private String validdays;
	/**
	 * 终端位置
	 */
	private String deviceposition;
	/**
	 * 凭证下发说明
	 */
	private String validatememo;
	/**
	 * 放映时间
	 */
	private String showdate;
	/**
	 * 座位号
	 */
	private String seatno;
	/**
	 * 影院名称
	 */
	private String cinemaname;
	/**
	 * 影片名称
	 */
	private String filmname;
	/**
	 * 下单时间
	 */
	private String ordertime;
	/**
	 * 订单号
	 */
	private String orderno;
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getGoodsnum() {
		return goodsnum;
	}
	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}
	public String getValiddays() {
		return validdays;
	}
	public void setValiddays(String validdays) {
		this.validdays = validdays;
	}
	public String getDeviceposition() {
		return deviceposition;
	}
	public void setDeviceposition(String deviceposition) {
		this.deviceposition = deviceposition;
	}
	public String getValidatememo() {
		return validatememo;
	}
	public void setValidatememo(String validatememo) {
		this.validatememo = validatememo;
	}
	public String getShowdate() {
		return showdate;
	}
	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}
	public String getSeatno() {
		return seatno;
	}
	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}
	public String getCinemaname() {
		return cinemaname;
	}
	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}
	public String getFilmname() {
		return filmname;
	}
	public void setFilmname(String filmname) {
		this.filmname = filmname;
	}
	public String getVoucherno() {
		return voucherno;
	}
	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
}
