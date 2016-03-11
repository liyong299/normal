package com.mopon.entityResult;
/*
 * 带结果集的result
 * */
public class ResultT<T>{
	/****是否成功*****/
	private Boolean isSuccess = false;
	/****消息****/
	private String message = "";
	/****结果集****/
	private T result = null;
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
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
}