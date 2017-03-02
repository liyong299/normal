/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.longconnection.entries
 * 文件名称：LoginMsg.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月2日 下午4:29:49
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.longconnection.entries;

/**
 * @功能描述：
 * @文件名称：LoginMsg.java
 * @author ly
 */
public class LoginMsg extends BaseMsg {

	private String userName;
	private String password;

	public LoginMsg() {
		this.setType(MsgType.LOGIN);
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
