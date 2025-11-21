package org.pro.newserver.domain.user.exception;

import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
  public UserNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
