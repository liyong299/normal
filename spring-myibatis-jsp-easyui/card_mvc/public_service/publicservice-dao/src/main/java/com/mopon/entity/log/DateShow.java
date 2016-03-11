package com.mopon.entity.log;

/**
 * 页面日期时间
 * 
 * @author liuxi
 *
 */
public class DateShow {
	//记录开始日期
	private String createDateBagin;
	
	//记录结束日期
	private String createDateEnd;

	//页面显示日期时间
	private String recTimeShow;
	
	public String getCreateDateBagin() {
		return createDateBagin;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateBagin(String createDateBagin) {
		this.createDateBagin = createDateBagin;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getRecTimeShow() {
		return recTimeShow;
	}

	public void setRecTimeShow(String recTimeShow) {
		this.recTimeShow = recTimeShow;
	}
	
	

}
