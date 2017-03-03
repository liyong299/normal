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

import java.util.HashMap;
import java.util.Map;

/**
 * @功能描述：业务消息
 * @文件名称：BussinessMsg.java
 * @author ly
 */
public class RequestMsg extends BaseMsg {

	// 请求参数暂时简化成Map
	private Map<String, String> params;

	public RequestMsg() {
		this.setType(MsgType.REQUEST);
	}

	public RequestMsg genDemo(BaseMsg msg) {
		this.setAuth(msg.getAuth());
		this.setClientId(msg.getClientId());
		this.setContentType(msg.getContentType());
		this.setEncryption(msg.getEncryption());
		this.params = new HashMap<String, String>();
		params.put("time", "today");
		return this;
	}

	/**
	 * @return the params
	 */
	public Map<String, String> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
