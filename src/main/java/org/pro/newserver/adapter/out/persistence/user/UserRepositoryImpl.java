package org.pro.newserver.adapter.out.persistence.user;

import lombok.RequiredArgsConstructor;
import org.pro.newserver.domain.user.infrastructure.UserRepository;
import org.pro.newserver.domain.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;
	private final UserJpaMapper userJpaMapper;

	@Override
	public User save(User user) {
		UserJpaEntity entity = userJpaMapper.toEntity(user);
		UserJpaEntity saved = userJpaRepository.save(entity);
		return userJpaMapper.toDomain(saved);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userJpaRepository.findById(id)
			.map(userJpaMapper::toDomain);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userJpaRepository.findByEmail(email)
			.map(userJpaMapper::toDomain);
	}

	@Override
	public Optional<User> findByName(String name) {
		return userJpaRepository.findByName(name)
			.map(userJpaMapper::toDomain);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userJpaRepository.existsByEmail(email);
	}
}
