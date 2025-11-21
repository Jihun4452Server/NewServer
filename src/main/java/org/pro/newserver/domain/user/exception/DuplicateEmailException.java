package org.pro.newserver.domain.user.exception;

import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.BusinessException;

public class DuplicateEmailException extends BusinessException {
  public DuplicateEmailException(ErrorCode errorCode) {
    super(errorCode);
  }
}
