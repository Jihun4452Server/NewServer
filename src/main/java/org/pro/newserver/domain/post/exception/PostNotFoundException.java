package org.pro.newserver.domain.post.exception;

import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.BusinessException;

public class PostNotFoundException extends BusinessException {
	public PostNotFoundException() {
		super(ErrorCode.POST_NOT_FOUND);
	}
}
