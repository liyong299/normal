/**
 * 
 */
package com.mopon.service.contant;


/**
 * 
 * 只订单详情接口 返回凭证信息下
 * @author moponyf00111
 *
 */
public class DisVoucher {

	/**
	 * 凭证号
	 */
	private String voucherNo;
	/**
	 * beginTime 始时间:如2015-11
	 */
	private String begintime;
	/**
	 * 结束时间
	 */
	private String endTime;
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
