/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.longconnection.entries
 * 文件名称：BussinessMsg.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月2日 下午4:32:51
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.longconnection.entries;


/**
 * @功能描述：业务消息
 * @文件名称：BussinessMsg.java
 * @author ly
 */
public class ResponeMsg extends BaseMsg {

	// 响应对象暂时简化成Map
	private String content;

	public ResponeMsg() {
		this.setType(MsgType.RESPONSE);
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
