package com.mopon.entity.log;

/**
 * 接口日志
 * 
 * @author liuxi
 *
 */
public class AccessApiLog extends DateShow{
	private Long logID;
	private Long sysLogID;
	//接入商编号
	private String accessProviderNo;
	//接口名称
	private String apiName;
	//接口地址
	private String apiUrl;
	//入参
	private String inParam;
	//出参
	private String outParam;
	//描述
	private String memo;
	//日志状态
	private String logStatus;
	//记录时间
	private Integer recTime;

	
	public Long getLogID() {
		return logID;
	}

	public Long getSysLogID() {
		return sysLogID;
	}

	public String getAccessProviderNo() {
		return accessProviderNo;
	}

	public String getApiName() {
		return apiName;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public String getInParam() {
		return inParam;
	}

	public String getOutParam() {
		return outParam;
	}

	public String getMemo() {
		return memo;
	}

	public String getLogStatus() {
		return logStatus;
	}

	public Integer getRecTime() {
		return recTime;
	}

	public void setLogID(Long logID) {
		this.logID = logID;
	}

	public void setSysLogID(Long sysLogID) {
		this.sysLogID = sysLogID;
	}

	public void setAccessProviderNo(String accessProviderNo) {
		this.accessProviderNo = accessProviderNo;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public void setInParam(String inParam) {
		this.inParam = inParam;
	}

	public void setOutParam(String outParam) {
		this.outParam = outParam;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public void setRecTime(Integer recTime) {
		this.recTime = recTime;
	}

}
