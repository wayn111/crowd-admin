package com.wayn.common.exception;

/**
 * 自定义业务异常
 */
public class BusinessException extends RuntimeException {

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

}
