package com.mopon.entity.daobase;
/*
 * 不带结果集的result
 * */
public class Result{
	/****是否成功*****/
	private Boolean isSuccess = false;
	/****消息****/
	private String message = "";
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}