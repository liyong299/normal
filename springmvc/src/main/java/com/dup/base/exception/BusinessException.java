package com.dup.base.exception;

/**
 * 业务异常。
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
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
}