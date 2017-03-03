/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.longconnection
 * 文件名称：BaseMsg.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月2日 下午4:22:57
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.longconnection.entries;

/**
 * @功能描述：基本消息内容,应该区分消息头部和消息体
 * @文件名称：BaseMsg.java
 * @author ly
 */
public class BaseMsg {

	private String clientId;
	private String encryption;
	private String auth;
	private MsgType type;
	private String contentType;

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the encryption
	 */
	public String getEncryption() {
		return encryption;
	}

	/**
	 * @param encryption the encryption to set
	 */
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

	/**
	 * @return the type
	 */
	public MsgType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(MsgType type) {
		this.type = type;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}
}
