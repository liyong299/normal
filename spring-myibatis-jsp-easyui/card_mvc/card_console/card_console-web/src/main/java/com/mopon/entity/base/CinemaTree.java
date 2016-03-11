package com.mopon.entity.base;

import java.util.List;

public class CinemaTree {

	private Integer id;

	private String text;

	private Boolean lines = true;

	private String state = "open";

	private String iconCls = "icon-hide";

	private Boolean checked = false;

	private String provinceCode;

	private String cityCode;

	private String countyCode;

	private String cinemaCode;

	private String hallCode;

	private List<CinemaTree> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getLines() {
		return lines;
	}

	public void setLines(Boolean lines) {
		this.lines = lines;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCinemaCode() {
		return cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public List<CinemaTree> getChildren() {
		return children;
	}

	public void setChildren(List<CinemaTree> children) {
		this.children = children;
	}

	public String getHallCode() {
		return hallCode;
	}

	public void setHallCode(String hallCode) {
		this.hallCode = hallCode;
	}
}