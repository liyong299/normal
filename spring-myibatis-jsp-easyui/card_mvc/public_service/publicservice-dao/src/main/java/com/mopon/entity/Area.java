package com.mopon.entity;

import java.util.List;

/**
 * 区域信息
 * @author liliang
 *
 */
public class Area {

	//区域id
	private Integer areaId;
	
	//区域编码
	private String areaNo;
	
	//区域名称
	private String areaName;
	
	//父级编号
	private String parentNo;
	
	//区域级别
	private Integer areaLevel;
	
	//缩写
	private String chn;
	
	//是否开启
	private  Integer isUsed;
	
	//是否热门
	private Integer isHot;
	
	//排序编号
	private Integer sortNo;
	
	//子区域集合
	private List<Area> childAreas;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}

	public Integer getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getChn() {
		return chn;
	}

	public void setChn(String chn) {
		this.chn = chn;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public List<Area> getChildAreas() {
		return childAreas;
	}

	public void setChildAreas(List<Area> childAreas) {
		this.childAreas = childAreas;
	}
	
}
