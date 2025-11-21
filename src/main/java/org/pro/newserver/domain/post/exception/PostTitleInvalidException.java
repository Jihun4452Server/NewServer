package org.pro.newserver.domain.post.exception;

import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.BusinessException;

public class PostTitleInvalidException extends BusinessException {
	public PostTitleInvalidException() {
		super(ErrorCode.POST_TITLE_INVALID);
	}
}
