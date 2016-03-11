package com.mopon.entity.base;

import java.util.List;
/*
 * 带列表结果集的result
 * */
public class ResultList<T>{
	/****是否成功*****/
	private Boolean isSuccess = false;
	/****消息****/
	private String message = "";
	/****结果集****/
	private List<T> rows = null;
	private List<Object> footer = null;
	
	/****行总数****/
	private int total = 0;
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
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Object> getFooter() {
		return footer;
	}
	public void setFooter(List<Object> footer) {
		this.footer = footer;
	}
}