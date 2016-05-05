package com.mopon.card.api;

/**
 * 
 * API响应。
 * 
 */
public enum ApiReplyCode implements IReplyCode {
	SUCCESS("001", "操作成功。"), CHANNEL_NOT_EXIST("900", "用户未找到。"), CHANNEL_CLOSED(
			"901", "用户已关闭。"), CHANNEL_UNSALABLE("902", "用户已停售。"), 
			TRANSACTIONEXCEPTION("993", "事务发生回滚异常。"), 
			REPEAT_ACCESS("994", "重复异常请求访问被禁止。"), CONNECT_FAILED("995", "网络连接异常。"),
			ACCESS_DENIDE("996", "访问被禁止。"), PARAMS_VALIDATE_FAILED("997", "参数验证失败。"), 
			SIGN_VERIFY_FAILED("998", "签名验证失败。"), FAILED("999", "发生未知异常。");

	private String code;
	private String msg;

	/**
	 * 构造方法。
	 * 
	 * @param code
	 *            编码
	 * @param msg
	 *            信息
	 */
	private ApiReplyCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}