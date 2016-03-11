package com.mopon.card.api.facade.v1.vo;

import java.io.Serializable;

import com.mopon.entity.console.base.BaseAccountExt;


public class UserVo implements Serializable 
{
	/**
	 * serialVersionUID属性说明:
	 */
	private static final long serialVersionUID = -299956150479071997L;
	/** 名称 */
	private String userName = "";
	

	/**
	 * 构造方法。
	 * 
	 * @param cinema
	 *            影院
	 * @param path
	 *            服务器图片路径
	 */
	public UserVo(BaseAccountExt user) {
		this.userName = user.getName();
	}


	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}