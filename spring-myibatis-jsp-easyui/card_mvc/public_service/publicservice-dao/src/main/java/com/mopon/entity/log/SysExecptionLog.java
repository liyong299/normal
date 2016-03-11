package com.mopon.entity.log;

/**
 * 系统日志
 * @author liuxi
 *
 */
public class SysExecptionLog extends DateShow{

	private Long logID;
	//应用程序名
	private String applicationName;
	//方法名
	private String funName;
	//异常信息
	private String expMsg;
	//异常堆栈
	private String expStackTrace;
	//记录时间
	private Integer recTime;

	public Long getLogID() {
		return logID;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public String getFunName() {
		return funName;
	}

	public String getExpMsg() {
		return expMsg;
	}


	public void setLogID(Long logID) {
		this.logID = logID;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public void setExpMsg(String expMsg) {
		this.expMsg = expMsg;
	}

	public String getExpStackTrace() {
		return expStackTrace;
	}

	public Integer getRecTime() {
		return recTime;
	}

	public void setExpStackTrace(String expStackTrace) {
		this.expStackTrace = expStackTrace;
	}

	public void setRecTime(Integer recTime) {
		this.recTime = recTime;
	}


}
