package com.mopon.entity;
import java.util.Date;
/**
 * 角色
 * @author 王贤志
 *
 */
public class BaseRole{
	//操作ID
	private Integer id;

	//角色名称
	private String name;
	
	//是否系统
	private Integer system;
	
	//创建时间
	private Date createTime;

	//创建人
	private String creator;
	
	//修改时间
	private Date modifiedDate;
	
	//修改人
	private String modifier;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSystem() {
		return system;
	}

	public void setSystem(Integer system) {
		this.system = system;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
}