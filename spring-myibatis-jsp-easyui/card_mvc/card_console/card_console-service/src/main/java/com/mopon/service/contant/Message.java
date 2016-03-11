/**
 * 
 */
package com.mopon.service.contant;

/**
 * <p>Description: 返回失败、成功message</p>
 * @date 17 Apr 2015
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Message {
	
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 原因
	 */
	private String msg;
	
	public Message(String code,String msg){
		this.code = code;
		this.msg =  msg;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
