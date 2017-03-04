/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.longconnection.entries
 * 文件名称：PingMsg.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月2日 下午4:31:47
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.longconnection.entries;

/**
 * @功能描述：ping消息
 * @文件名称：PingMsg.java
 * @author ly
 */
public class PingMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8047178895267509225L;
	private static PingMsg msg = new PingMsg();

	public static PingMsg getInstance() {
		return msg;
	}

	private PingMsg() {
		this.setType(MsgType.PING);
	}

}
