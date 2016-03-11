/**
 * 
 */
package com.mopon.util.entity;

/**
 * 
 * <p>Description:订单</p>
 * @date 2015年4月14日
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Order {

	/**
	 * 平台订单号
	 */
	private String code;
	/**
	 * 渠道订单号
	 */
	private String channelOrderCode;
	/**
	 * 确认时间（格式: yyyy-MM-dd HH:mm:ss）
	 */
	private String confirmTime;
	/**
	 * 选座票数量
	 */
	private String ticketCount;
	/**
	 *　	订单金额
	 */
	private double amount;
	/**
	 *  短信类型（1：地面取票号；2：平台凭证号；3：混合方式）
	 */
	private String printMode;
	/**
	 * 接入商类型（1：火烈鸟 2：鼎新 3：火凤凰 4：满天星）
	 */
	private String provider;
	/**
	 * 凭证号
	 */
	private String voucherCode;
	/**
	 * 取票号
	 */
	private String printCode;
	/**
	 * 取票验证码
	 */
	private String verifyCode;
	/**
	 * 订单状态（1：未支付 2：已取消 3：已支付 4：出票成功 5：出票失败 6：已退票）
	 */
	private String status;
	/**
	 * 打印状态（0：未打印 1：已打印）
	 */
	private String printStatus;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getChannelOrderCode() {
		return channelOrderCode;
	}
	public void setChannelOrderCode(String channelOrderCode) {
		this.channelOrderCode = channelOrderCode;
	}
	public String getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(String ticketCount) {
		this.ticketCount = ticketCount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
}
