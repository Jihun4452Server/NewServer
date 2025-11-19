package org.pro.newserver.application.user.service.Impl;

import lombok.RequiredArgsConstructor;
import org.pro.newserver.application.user.UserMapper;
import org.pro.newserver.application.user.dto.UserCommand;
import org.pro.newserver.application.user.service.UserService;
import org.pro.newserver.domain.user.infrastructure.UserRepository;
import org.pro.newserver.domain.user.model.User;
import org.pro.newserver.application.user.validator.UserValidator;
import org.pro.newserver.global.error.ErrorCode;
import org.pro.newserver.global.error.exception.UnauthorizedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserValidator userValidator;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public Long saveUser(UserCommand command) {
		userValidator.validateName(command.name());
		userValidator.validateDuplicateEmail(command.email());

		User user = userMapper.toUser(command,passwordEncoder);
		User saved = userRepository.save(user);
		return saved.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public void login(String email, String password) {
		User user = userValidator.getUserByEmailOrThrow(email);

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new UnauthorizedException(ErrorCode.AUTHENTICATION_FAILED);
		}
	}
}
