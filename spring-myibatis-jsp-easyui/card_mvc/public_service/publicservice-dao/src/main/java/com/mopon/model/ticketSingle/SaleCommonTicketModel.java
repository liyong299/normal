package com.mopon.model.ticketSingle;

public class SaleCommonTicketModel {
	//通兑票名称
	private String name;
	// 销售渠道类型
	private String channel_type;
	// 状态
	private String status;
	// 影院
	private String cinemano;
	//创建时间
	private String create_date_bagin;
	private String create_date_end;
	// 有效期限
	private String valid_stime;
	private String valid_etime;
	// 有效期限(天)
	private String valid_days_begin;
	private String valid_days_end;
	// 规格
	private String show_type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChannel_type() {
		return channel_type;
	}
	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCinemano() {
		return cinemano;
	}
	public void setCinemano(String cinemano) {
		this.cinemano = cinemano;
	}
	public String getCreate_date_bagin() {
		return create_date_bagin;
	}
	public void setCreate_date_bagin(String create_date_bagin) {
		this.create_date_bagin = create_date_bagin;
	}
	public String getCreate_date_end() {
		return create_date_end;
	}
	public void setCreate_date_end(String create_date_end) {
		this.create_date_end = create_date_end;
	}
	public String getValid_stime() {
		return valid_stime;
	}
	public void setValid_stime(String valid_stime) {
		this.valid_stime = valid_stime;
	}
	public String getValid_etime() {
		return valid_etime;
	}
	public void setValid_etime(String valid_etime) {
		this.valid_etime = valid_etime;
	}
	public String getValid_days_begin() {
		return valid_days_begin;
	}
	public void setValid_days_begin(String valid_days_begin) {
		this.valid_days_begin = valid_days_begin;
	}
	public String getValid_days_end() {
		return valid_days_end;
	}
	public void setValid_days_end(String valid_days_end) {
		this.valid_days_end = valid_days_end;
	}
	public String getShow_type() {
		return show_type;
	}
	public void setShow_type(String show_type) {
		this.show_type = show_type;
	}
	@Override
	public String toString() {
		return "SaleCommonTicketModel [name=" + name + ", channel_type="
				+ channel_type + ", status=" + status + ", cinemano="
				+ cinemano + ", create_date_bagin=" + create_date_bagin
				+ ", create_date_end=" + create_date_end + ", valid_stime="
				+ valid_stime + ", valid_etime=" + valid_etime
				+ ", valid_days_begin=" + valid_days_begin
				+ ", valid_days_end=" + valid_days_end + ", show_type="
				+ show_type + "]";
	}
	
}
