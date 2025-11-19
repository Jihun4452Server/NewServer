package org.pro.newserver.application.user.service.Impl;

import lombok.RequiredArgsConstructor;

import org.pro.newserver.domain.user.infrastructure.UserRepository;
import org.pro.newserver.domain.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	// (참고용) 회원 저장 – 이름 검증 + 이메일 중복 체크
	@Transactional
	public Long saveUser(SaveUserCommand command) {
		if (!User.isValidName(command.getName())) {
			throw new InvalidUserNameException(command.getName());
		}
		if (userRepository.existsByEmail(command.getEmail())) {
			throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
		}

		User user = User.builder()
			.name(command.getName())
			.email(command.getEmail())
			.password(command.getPassword()) // 실제 서비스라면 해시 필요
			.gender(command.getGender())
			.build();

		User saved = userRepository.save(user);
		return saved.getId();
	}

	// 1) 이름 + 비밀번호로 이메일 찾기
	@Transactional(readOnly = true)
	public String findEmailByNameAndPassword(String name, String password) {
		return userRepository.findByNameAndPassword(name, password)
			.map(User::getEmail)
			.orElse(null);   // 못 찾으면 null. 필요하면 예외 던져도 됨
	}

	// 2) 이메일 중복 여부 확인
	@Transactional(readOnly = true)
	public boolean isEmailDuplicated(String email) {
		return userRepository.existsByEmail(email);
	}

	// 3) 이름이 장난스러운 이름인지 확인 (true면 정상, false면 장난 이름)
	@Transactional(readOnly = true)
	public boolean isNameAllowed(String name) {
		return User.isValidName(name);
	}
}
