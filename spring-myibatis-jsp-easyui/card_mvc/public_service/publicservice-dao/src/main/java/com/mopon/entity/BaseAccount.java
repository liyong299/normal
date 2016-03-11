package com.mopon.entity;

import java.util.Date;

/**
 * 用户
 * @author 陈誉
 *
 */
public class BaseAccount{
	//ID
	private Integer id;

	//角色ID
	private Integer roleID;

	//用户名
	private String name;

	//密码
	private String password;
	
	// 用户的key
	private String key;

	//姓名
	private String fullName;
	
	//状态
	private Integer status = -1;

	//登录次数
	private Integer loginTimes;

	//上次登录时间
	private Date lastTime;

	//创建时间
	private Date createTime;

	//创建人
	private String creator;

	//修改时间
	private Date modifiedDate = new Date();
	
	//修改人
	private String modifier;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
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

	/**
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key)
	{
		this.key = key;
	}
}