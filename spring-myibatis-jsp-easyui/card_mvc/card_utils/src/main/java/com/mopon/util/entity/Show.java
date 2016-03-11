/**
 * 
 */
package com.mopon.util.entity;

import java.util.List;

/**
 * <p>Description: 场次类 </p>
 * @date 2015年4月14日
 * @author Aaron
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Show {

	public Show(){}
	
	/**
	 * 渠道场次编码
	 */
	private String channelShowCode;
	/**
	 * 影院编码
	 */
	private String cinemaCode;
	/**
	 * 影院场次编码
	 */
	private String cinemaShowCode;
	/**
	 * 影厅编码
	 */
	private String hallCode;
	/**
	 * 影厅名称
	 */
	private String hallName;
	/**
	 * 影片编码
	 */
	private String filmCode;
	/**
	 * 影片名称
	 */
	private String filmName;
	/**
	 * 语言
	 */
	private String language;
	/**
	 * 放映时间yyyy-MM-dd HH:mm:ss
	 */
	private String showTime;
	/**
	 * 时长
	 */
	private String duration;
	/**
	 * 停售时间 yyyy-MM-dd HH:mm:ss
	 */
	private String stopSellTime;
	/**
	 */
	private String showType;
	/**
	 * 标准价
	 */
	private double stdPrice;
	/**
	 * 最低价
	 */
	private double minPrice;
	/**
	 * 结算价
	 */
	private double settlePrice;
	/**
	 * 票房价
	 */
	private double submitPrice;
	/**
	 * 接入费用
	 */
	private double connectFee;
	/**
	 * 状态 0下架 1：上架 2：失效
	 */
	private int status;
	/**
	 * 状态 0下架 1：上架 2：失效
	 */	
	private String showStatus;
	/**
	 * 接入商类型（1：火烈鸟 2：鼎新 3：火凤凰 4：满天星 8：微信）
	 */
	private int provider;
	
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 影院名称
	 */
	private String cinemaName;
	/**
	 * 上映时间从
	 */	
	private String showTime_Start;
	/**
	 * 上映时间到
	 */
	private String showTime_End;

	/**
	 * 请求的渠道号
	 */
	private String channelCode;
	
	private String createTime;
	private List<Object> benefitCardPrices;
	/**
	 * 创建时间范围
	 */
	private String createTime_Start;
	private String createTime_End;
//	private String benefitCardPrice;
	public String getChannelShowCode() {
		return channelShowCode;
	}
	public void setChannelShowCode(String channelShowCode) {
		this.channelShowCode = channelShowCode;
	}
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	public String getCinemaShowCode() {
		return cinemaShowCode;
	}
	public void setCinemaShowCode(String cinemaShowCode) {
		this.cinemaShowCode = cinemaShowCode;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStopSellTime() {
		return stopSellTime;
	}
	public void setStopSellTime(String stopSellTime) {
		this.stopSellTime = stopSellTime;
	}
	public double getStdPrice() {
		return stdPrice;
	}
	public void setStdPrice(double stdPrice) {
		this.stdPrice = stdPrice;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(double settlePrice) {
		this.settlePrice = settlePrice;
	}
	public double getSubmitPrice() {
		return submitPrice;
	}
	public void setSubmitPrice(double submitPrice) {
		this.submitPrice = submitPrice;
	}
	public double getConnectFee() {
		return connectFee;
	}
	public void setConnectFee(double connectFee) {
		this.connectFee = connectFee;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<Object> getBenefitCardPrices() {
		return benefitCardPrices;
	}
	public void setBenefitCardPrices(List<Object> benefitCardPrices) {
		this.benefitCardPrices = benefitCardPrices;
	}
	public int getProvider() {
		return provider;
	}
	public void setProvider(int provider) {
		this.provider = provider;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getShowTime_Start() {
		return showTime_Start;
	}
	public void setShowTime_Start(String showTime_Start) {
		this.showTime_Start = showTime_Start;
	}
	public String getShowTime_End() {
		return showTime_End;
	}
	public void setShowTime_End(String showTime_End) {
		this.showTime_End = showTime_End;
	}
	public String getShowStatus() {
		return showStatus;
	}
	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}
	public String getCreateTime_Start() {
		return createTime_Start;
	}
	public void setCreateTime_Start(String createTime_Start) {
		this.createTime_Start = createTime_Start;
	}
	public String getCreateTime_End() {
		return createTime_End;
	}
	public void setCreateTime_End(String createTime_End) {
		this.createTime_End = createTime_End;
	}
	
}
