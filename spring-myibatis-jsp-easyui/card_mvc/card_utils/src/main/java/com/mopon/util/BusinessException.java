package com.mopon.util;


/**
 * 业务异常。
 */
public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8143679612476630027L;

	/**
	 * 构造方法。
	 * 
	 * @param message
	 *            异常信息
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * 构造方法。
	 * 
	 * @param cause
	 *            异常原因
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造方法。
	 * 
	 * @param message
	 *            异常信息
	 * @param cause
	 *            异常原因
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public static void raise(String msg) {
		throw new BusinessException(msg);
	}

	
	
}