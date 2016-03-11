package com.mopon.card.api;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 请求对象基类。
 */
public abstract class ApiQuery implements Serializable {
	/**
	 * serialVersionUID属性说明:
	 */
	private static final long serialVersionUID = 5525518675852817128L;
	/** 对端注册的编号 */
	@NotBlank(message = "编号不能为空。")
	protected String appCode;
	
	/** 签名 */
	@NotBlank(message = "签名不能为空。")
	protected String sign;


	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the appCode
	 */
	public String getAppCode()
	{
		return appCode;
	}

	/**
	 * @param appCode the appCode to set
	 */
	public void setAppCode(String appCode)
	{
		this.appCode = appCode;
	}
}