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

	@Override
	public User save(User user) {
		UserJpaEntity entity = UserJpaEntity.fromDomain(user);
		UserJpaEntity saved = userJpaRepository.save(entity);
		return saved.toDomain();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userJpaRepository.findById(id)
			.map(UserJpaEntity::toDomain);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userJpaRepository.findByEmail(email)
			.map(UserJpaEntity::toDomain);
	}

	@Override
	public Optional<User> findByNameAndPassword(String name, String password) {
		return userJpaRepository.findByNameAndPassword(name, password)
			.map(UserJpaEntity::toDomain);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userJpaRepository.existsByEmail(email);
	}
}
