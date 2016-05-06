package com.dup.server.daos.entities;

import java.io.Serializable;

/**
 * 影院。
 */
public class Cinema extends BaseEntity implements Comparable<Cinema>,
		Serializable {
	/**
	 * serialVersionUID属性说明:
	 */
	private static final long serialVersionUID = 1380867178988116718L;

	private String id;
	
	private String code;

	private String name;

	/** 影厅数量 */
	private Integer hallCount = 0;

	private String address;


	private String url;

	private String tel;

	private String devicePos;

	/** 综合评分 */
	private Double grade = 8.0D;
	/** 简介 */
	private String intro;
	/** 公交路线 */
	private String busLine;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	/** 特色 */
	private String feature;
	/** 排序 */
	private Integer ordinal = 0;
	private Boolean salable = true;
	
	/** 选座票设置状态 */
	private Boolean ticketSetted = false;
	/** 会员卡设置状态 */

	private Boolean memberSetted = false;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHallCount() {
		return hallCount;
	}

	public void setHallCount(Integer hallCount) {
		this.hallCount = hallCount;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDevicePos() {
		return devicePos;
	}

	public void setDevicePos(String devicePos) {
		this.devicePos = devicePos;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getBusLine() {
		return busLine;
	}

	public void setBusLine(String busLine) {
		this.busLine = busLine;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Boolean getSalable() {
		return salable;
	}

	public void setSalable(Boolean salable) {
		this.salable = salable;
	}

	public Boolean getTicketSetted() {
		return ticketSetted;
	}

	public void setTicketSetted(Boolean ticketSetted) {
		this.ticketSetted = ticketSetted;
	}

	public Boolean getMemberSetted() {
		return memberSetted;
	}

	public void setMemberSetted(Boolean memberSetted) {
		this.memberSetted = memberSetted;
	}

	@Override
	public int compareTo(Cinema o) {
	    return this.getCode().compareTo(o.getCode());
	}

	/**
	 * @return the id
	 */
	public String getId() {
	    return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
	    this.id = id;
	}
}
