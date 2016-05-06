/**
 * 项目名称：mvc.core
 * 文件包名：com.dup.server.daos.entities
 * 文件名称：BaseEntity.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月6日 上午11:59:25
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.server.daos.entities;

import java.util.Date;

/**
 * 功能描述：<p color="red">所有实体的父类</p>
 * 文件名称：BaseEntity.java
 * @author ly
 */
public class BaseEntity {

	//创建时间
	private Date createTime;

	//创建人
	private String creator;

	//修改时间
	private Date modifiedDate = new Date();
	
	//修改人
	private String modifier;

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
	    return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
	    this.createTime = createTime;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
	    return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
	    this.creator = creator;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
	    return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
	    this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the modifier
	 */
	public String getModifier() {
	    return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
	    this.modifier = modifier;
	}
}
