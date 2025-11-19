package org.pro.newserver.domain.user.validator;

import lombok.RequiredArgsConstructor;

import org.pro.newserver.domain.user.exception.DuplicateEmailException;
import org.pro.newserver.domain.user.exception.InvalidUserNameException;
import org.pro.newserver.domain.user.exception.UserNotFoundException;
import org.pro.newserver.domain.user.infrastructure.UserRepository;
import org.pro.newserver.domain.user.model.User;
import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

	private final UserRepository userRepository;

	public void validateDuplicateEmail(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new DuplicateEmailException(ErrorCode.EMAIL_ALREADY_EXISTS);
		}
	}

	public void validateName(String name) {
		if (name == null || name.isBlank()) {
			throw new InvalidUserNameException(ErrorCode.USER_NAME_FAILED);
		}

		String trimmed = name.trim();
		if (trimmed.length() < 2 || trimmed.length() > 20) {
			throw new InvalidUserNameException(ErrorCode.USER_NAME_FAILED);
		}
	}

	public User getUserByName(String name) {
		return userRepository.findByName(name)
			.orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
	}

	public User getUserByEmailOrThrow(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
	}
}
