package org.pro.newserver.global.error.exception;

import org.pro.newserver.global.error.ErrorCode;

public class UnauthorizedException extends BusinessException{
	public UnauthorizedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
