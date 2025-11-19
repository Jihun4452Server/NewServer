package org.pro.newserver.domain.user.exception;

import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.BusinessException;

public class InvalidUserNameException extends BusinessException {
	public InvalidUserNameException(ErrorCode errorCode) {
		super(errorCode);
	}
}
