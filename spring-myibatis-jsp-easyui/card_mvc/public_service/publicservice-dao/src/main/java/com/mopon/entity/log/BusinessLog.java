package com.mopon.entity.log;

/**
 * 业务日志
 * @author liuxi
 *
 */
public class BusinessLog  extends DateShow{
	//记录ID
	private Integer logID;
	//关联异常日志ID
	private Integer sysLogID;
	//业务类型字典值
	private String bizTypeValue;
	//业务类型
	private String bizType;
	//业务步骤字典值
	private String bizStepValue;
	//业务步骤
	private String bizStep;
	//订单号
	private String orderNo;
	//凭证
	private String voucherCode;
	//手机号
	private String mobileNo;
	//影院编号
	private String cinemaNo;
	//描述
	private String memo;
	//日志状态:0=正常，1=异常
	private String logStatus;
	//记录时间
	private Long recTime;

	public Integer getLogID() {
		return logID;
	}

	public Integer getSysLogID() {
		return sysLogID;
	}

	public String getBizTypeValue() {
		return bizTypeValue;
	}

	public String getBizType() {
		return bizType;
	}

	public String getBizStepValue() {
		return bizStepValue;
	}

	public String getBizStep() {
		return bizStep;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getCinemaNo() {
		return cinemaNo;
	}

	public String getMemo() {
		return memo;
	}

	public String getLogStatus() {
		return logStatus;
	}

	public Long getRecTime() {
		return recTime;
	}

	public void setLogID(Integer logID) {
		this.logID = logID;
	}

	public void setSysLogID(Integer sysLogID) {
		this.sysLogID = sysLogID;
	}

	public void setBizTypeValue(String bizTypeValue) {
		this.bizTypeValue = bizTypeValue;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public void setBizStepValue(String bizStepValue) {
		this.bizStepValue = bizStepValue;
	}

	public void setBizStep(String bizStep) {
		this.bizStep = bizStep;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setCinemaNo(String cinemaNo) {
		this.cinemaNo = cinemaNo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public void setRecTime(Long recTime) {
		this.recTime = recTime;
	}

}
