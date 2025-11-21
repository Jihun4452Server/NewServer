package org.pro.newserver.domain.post.exception;

import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.BusinessException;

public class PostContentInvalidException extends BusinessException {
	public PostContentInvalidException() {
		super(ErrorCode.POST_CONTENT_INVALID);
	}
}
